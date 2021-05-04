package de.htwg.se.stratego.model.FileIODatabase.fileIOSlick

import de.htwg.se.stratego.model.FileIODatabase.fileIODatabaseInterface
import play.api.libs.json.{JsArray, JsNumber, JsObject, JsValue, Json}
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.JdbcBackend.Database
import scala.collection.mutable.ListBuffer

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

case class FileIOSlick() extends fileIODatabaseInterface {
  //val dbUrl: String = "jdbc:postgresql://localhost:5432/Softwarearchitektur"
  val database = Database.forURL("jdbc:postgresql://db:5432/postgres", "postgres", "postgres", driver = "org.postgresql.Driver")

  val slickplayertable: TableQuery[SlickPlayer] = TableQuery[SlickPlayer]
  val slickmatchfieldtable: TableQuery[SlickMatchfield] = TableQuery[SlickMatchfield]
  val tables = List(slickplayertable, slickmatchfieldtable)


  tables.foreach(e => Await.result(database.run(e.schema.createIfNotExists), Duration.Inf))

  override def delete(): Unit = {
    Await.ready(database.run(slickplayertable.delete), Duration.Inf)
    Await.ready(database.run(slickmatchfieldtable.delete), Duration.Inf)
  }

  // insert (save)
  override def update(game: String): Unit = {
    //val game = "{\n\n\"currentPlayerIndex\" : 0,\n\n\"players\" : \"Sascha Benni\",\n\n\"matchField\" : [ {\n\n\"row\" : 0,\n\n\"col\" : 0,\n\n\"figName\" : \"9\",\n\n\"figValue\" : 9,\n\n\"colour\" : 0\n\n}, {\n\n\"row\" : 0,\n\n\"col\" : 1\n\n}, {\n\n\"row\" : 0,\n\n\"col\" : 2,\n\n\"figName\" : \"6\",\n\n\"figValue\" : 6,\n\n\"colour\" : 0\n\n}, {\n\n\"row\" : 0,\n\n\"col\" : 3,\n\n\"figName\" : \"F\",\n\n\"figValue\" : 0,\n\n\"colour\" : 0\n\n}, {\n\n\"row\" : 1,\n\n\"col\" : 0\n\n}, {\n\n\"row\" : 1,\n\n\"col\" : 1,\n\n\"figName\" : \"8\",\n\n\"figValue\" : 8,\n\n\"colour\" : 0\n\n}, {\n\n\"row\" : 1,\n\n\"col\" : 2\n\n}, {\n\n\"row\" : 1,\n\n\"col\" : 3\n\n}, {\n\n\"row\" : 2,\n\n\"col\" : 0\n\n}, {\n\n\"row\" : 2,\n\n\"col\" : 1,\n\n\"figName\" : \"8\",\n\n\"figValue\" : 8,\n\n\"colour\" : 1\n\n}, {\n\n\"row\" : 2,\n\n\"col\" : 2\n\n}, {\n\n\"row\" : 2,\n\n\"col\" : 3\n\n}, {\n\n\"row\" : 3,\n\n\"col\" : 0,\n\n\"figName\" : \"9\",\n\n\"figValue\" : 9,\n\n\"colour\" : 1\n\n}, {\n\n\"row\" : 3,\n\n\"col\" : 1\n\n}, {\n\n\"row\" : 3,\n\n\"col\" : 2,\n\n\"figName\" : \"6\",\n\n\"figValue\" : 6,\n\n\"colour\" : 1\n\n}, {\n\n\"row\" : 3,\n\n\"col\" : 3,\n\n\"figName\" : \"F\",\n\n\"figValue\" : 0,\n\n\"colour\" : 1\n\n} ]\n\n}"
    println(game)
    val json: JsValue = Json.parse(game)
    val newPlayerIndex = (json \ "currentPlayerIndex").get.toString().toInt
    val players = (json \ "players").get.toString()
    val sizeOfMatchfield: Int = (json \ "matchField").as[JsArray].value.size
    var matchfield = Matchfield(0, 0, 0, Option(""), Option(0), Option(0))
    var figName: String = ""
    var figValue: Int = 0
    var colour: Int = 0
    for (index <- 0 until sizeOfMatchfield) {
      val row = (json \\ "row") (index).as[Int]
      val col = (json \\ "col") (index).as[Int]
      if (((json \ "matchField") (index) \\ "figName").nonEmpty) {
        figName = ((json \ "matchField") (index) \ "figName").as[String]
        figValue = ((json \ "matchField") (index) \ "figValue").as[Int]
        colour = ((json \ "matchField") (index) \ "colour").as[Int]
      } else {
        figName = ""
        figValue = 0
        colour = 0
      }
      matchfield = Matchfield(0, row, col, Option(figName), Option(figValue), Option(colour))
      database.run(slickmatchfieldtable += matchfield)
    }
    database.run(slickplayertable += (0, newPlayerIndex, players, sizeOfMatchfield))
  }

  override def readMatchfield(): String = {
    val player: (Int, Int, String, Int) = readPlayer
    val matchfield: ListBuffer[Matchfield] = readMatchfieldfromdb
    val string = Json.prettyPrint(Json.obj(
      "currentPlayerIndex" -> JsNumber(player._2),
      "players" -> player._3,
      "matchField" -> Json.toJson(
        for {
          row <- 0 until Math.sqrt(player._4).toInt
          col <- 0 until Math.sqrt(player._4).toInt
        } yield {
          var obj = Json.obj(
            "row" -> row,
            "col" -> col
          )
          matchfield.foreach(f => {
            if (f.row == row && f.col == col && !f.figName.equals(Option(""))) {
              obj = obj.++(Json.obj(
                "figName" -> f.figName,
                "figValue" -> f.figValue,
                "colour" -> f.colour
              )
              )
            }
          })
          obj
        })))
    println(string)
    string
  }

  def readPlayer: (Int, Int, String, Int) = {
    val player@(id, playerIndex, players, sizeOfMatchfield) = Await.result(database.run(slickplayertable.result.head), Duration.Inf)
    println(id.toString + " " + playerIndex.toString + " " + players + " " + sizeOfMatchfield)
    (id, playerIndex, players, sizeOfMatchfield)
  }

  def readMatchfieldfromdb: ListBuffer[Matchfield] = {
    var matchfieldlist: ListBuffer[Matchfield] = ListBuffer.empty
    Await.result(database.run(slickmatchfieldtable.result.map(_.foreach(f => matchfieldlist.append(Matchfield(f.id, f.row, f.col, f.figName, f.figValue, f.colour))))), Duration.Inf)
    matchfieldlist.foreach(f => println(f))
    matchfieldlist
  }

}
