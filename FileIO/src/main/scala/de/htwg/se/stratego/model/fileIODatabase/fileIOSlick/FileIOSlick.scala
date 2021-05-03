package de.htwg.se.stratego.model.fileIODatabase.fileIOSlick

import akka.http.javadsl.common.EntityStreamingSupport.json
import de.htwg.se.stratego.model.fileIODatabase.fileIODatabaseInterface
import play.api.libs.json.{JsArray, JsNumber, JsValue, Json}
import slick.dbio.{DBIO, DBIOAction, Effect, NoStream}
import slick.jdbc.JdbcBackend.Database
import slick.driver.PostgresDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationLong}
import scala.util.parsing.json.{JSONArray, JSONObject}

class FileIOSlick extends fileIODatabaseInterface {
  //val dbUrl: String = "jdbc:postgresql://localhost:5432/Softwarearchitektur"
  val dbUrl: String = s"jdbc:postgresql://${sys.env.getOrElse("DATABASE_HOST", "localhost:5432")}/${sys.env.getOrElse("POSTGRES_DATABASE", "Softwarearchitektur")}" + "?serverTimezone=UTC&useSSL=false"
  val user: String = sys.env.getOrElse("POSTGRES_USER", "postgres")
  val password: String = sys.env.getOrElse("POSTGRES_PASSWORD", "12345678")

  val database = Database.forURL(
    url = dbUrl,
    driver = "org.postgresql.Driver",
    user = user,
    password = password
  )

  val slickplayertable: TableQuery[SlickPlayer] = TableQuery[SlickPlayer]
  val slickmatchfieldtable: TableQuery[SlickMatchfield] = TableQuery[SlickMatchfield]



  override def create(): Unit = {
    val queries: DBIOAction[Unit, NoStream, Effect.Schema] = DBIO.seq(
      slickplayertable.schema.createIfNotExists,
      slickmatchfieldtable.schema.createIfNotExists
    )
    val setup = database.run(queries)
    Await.result(setup, 5L.seconds)
    println(s"Settings, databaseUrl: ${dbUrl}, databaseUser: ${user}, databasePassword: ${password}")
  }

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
    var colour: Int  = 0
    for(index <- 0 until sizeOfMatchfield){
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      if(((json \ "matchField")(index) \\ "figName").nonEmpty) {
        figName = ((json \ "matchField")(index) \ "figName").as[String]
        figValue = ((json \ "matchField")(index) \ "figValue").as[Int]
        colour = ((json \ "matchField")(index) \ "colour").as[Int]
      } else {
        figName = ""
        figValue = 0
        colour = 0
      }
      matchfield = Matchfield(0, row, col, Option(figName), Option(figValue), Option(colour))
      database.run(slickmatchfieldtable += matchfield)
    }
    database.run(slickplayertable += (0, newPlayerIndex, players))
  }

  // load
  override def readPlayer(resid: Int): String = {
    val player@(id, playerIndex, players) = Await.result(database.run(slickplayertable.filter(_.id === resid).result.head), Duration.Inf)
    println(id + playerIndex + players)
    players
  }

  override def readMatchfield(resid: Int): String = {
    val matchfield@(matchf) = Await.result(database.run(slickmatchfieldtable.filter(_.id === resid).result.head), Duration.Inf)
    println(matchf.id + " " + matchf.row + " " + matchf.col + " " + matchf.figName.get + " " + matchf.figValue.get + " " + matchf.colour.get)
    matchf.figName.get
  }

  /**

  def toJson(): Unit = {
    Json.obj(
      "currentPlayerIndex" -> JsNumber(currentPlayerIndex),
      "players" -> players,
      "matchField"-> Json.toJson(
        for{
          row <- 0 until matchField.fields.matrixSize
          col <- 0 until matchField.fields.matrixSize
        } yield {
          var obj = Json.obj(
            "row" -> row,
            "col" -> col
          )
          if(matchField.fields.field(row,col).isSet) {
            obj = obj.++(Json.obj(
              "figName" -> matchField.fields.field(row, col).character.get.figure.name,
              "figValue" -> matchField.fields.field(row, col).character.get.figure.value,
              "colour" -> matchField.fields.field(row, col).colour.get.value
            )
            )
          }
          obj
        }
      )
    )
  } */
}
