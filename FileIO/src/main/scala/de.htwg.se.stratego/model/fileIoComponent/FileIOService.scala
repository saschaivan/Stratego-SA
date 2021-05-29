package de.htwg.se.stratego.model.fileIoComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCode}
import akka.http.scaladsl.server.Directives._
import com.google.inject.Guice
import de.htwg.se.stratego.model.fileIODatabase.{FileIODatabaseInterface, FileIODatabaseProxy, FileIOModule}
import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO

import scala.concurrent.Await
import scala.concurrent.duration.Duration


object FileIOService {

  def main(args: Array[String]): Unit = {

    val fileIO = new FileIO
    val injector = Guice.createInjector(new FileIOModule)
    val db = injector.getInstance(classOf[FileIODatabaseInterface])
    //val db = new FileIODatabaseProxy
    implicit val system = ActorSystem(Behaviors.empty, "fileIO")
    implicit val executionContext = system.executionContext

    val uri = "fileio_service"

    val port = 8081

    val route =
      concat (
        get {
          path("createTables") {
            //db.create()
            println("Tables created")
            complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
          }
        },
        post {
          path("savedb") {
            entity(as [String]) { game => {
              //println(game)
              db.update(1, game)
              //println("tables saved")
              complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
              }
            }
          }
        },
        get {
          path("loaddb") {
            //println("load json from db")
            complete(HttpEntity(ContentTypes.`application/json`, Await.result(db.read(1), Duration.Inf)))
          }
        },
        get {
          path("deletedb") {
            db.delete()
            println("data in database deleted")
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
