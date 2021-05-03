package de.htwg.se.stratego.model.fileIoComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode}
import akka.http.scaladsl.server.Directives._
import de.htwg.se.stratego.model.fileIODatabase.fileIOSlick.FileIOSlick
import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO


case object FileIOService {

  def main(args: Array[String]): Unit = {

    val fileIO = new FileIO
    val slickdb = new FileIOSlick
    implicit val system = ActorSystem(Behaviors.empty, "fileIO")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val uri = "fileio_service"

    val port = 8081

    val route =
      concat (
        get {
          path("createTables") {
            slickdb.create()
            println("Tables created")
            complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
          }
        },
        post {
          path("savedb") {
            entity(as [String]) { game => {
              //println(game)
              slickdb.update(game)
              println("tables saved")
              complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
              }
            }
          }
        },
        get {
          path("deletedb") {
            slickdb.delete()
            println("data in tables deleted")
            complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
          }
        },
        get {
          path("json") {
            println("load json")
            complete(HttpEntity(ContentTypes.`application/json`, fileIO.load_JSON))
          }
        },
        post {
          path("json") {
            entity(as [String]) { game =>
              fileIO.save(game)
              println("game saved")
              complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
            }
          }
        }

      )

    val bindingFuture = Http().newServerAt(uri, port).bind(route)
  }
}
