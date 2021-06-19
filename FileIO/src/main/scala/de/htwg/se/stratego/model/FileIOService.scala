package de.htwg.se.stratego.model

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCode}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import com.google.inject.Guice
import de.htwg.se.stratego.model.databaseComponent.FileIODatabaseProxy
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object FileIOService {

  def main(args: Array[String]): Unit = {

    val injector = Guice.createInjector(new FileIOModule)
    val fileIO = injector.getInstance(classOf[FileIOInterface])
    val db = new FileIODatabaseProxy
    implicit val system = ActorSystem(Behaviors.empty, "fileIO")
    implicit val executionContext = system.executionContext

    val uri = "fileio_service"

    val port = 8081

    val route =
      concat(
        post {
          path("savedb") {
            entity(as[String]) { game => {
              db.update(1, Future(game))
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
          path("fileIO") {
            println("load json")
            complete(HttpEntity(ContentTypes.`application/json`, fileIO.load))
          }
        },
        post {
            path("fileIO/json") {
              entity(as[String]) { game =>
                println("game saved json")
                fileIO.save(game)
                complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
              }
            }
        }
      )
    val bindingFuture = Http().newServerAt(uri, port).bind(route)
  }
}
