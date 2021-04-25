package de.htwg.se.stratego.model.fileIoComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO


case object FileIOService {

  def main(args: Array[String]): Unit = {

    val fileIO = new FileIO
    implicit val system = ActorSystem(Behaviors.empty, "fileIO")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val port = 8082

    val uri = "0.0.0.0"

    val route =
      concat (
        get {
          path("json") {
            complete(HttpEntity(ContentTypes.`application/json`, fileIO.load_JSON))
          }
        },
        post {
          path("json") {
            entity(as [String]) { game =>
              fileIO.save(game)
              println("GAME SAVED")
              complete("game saved")
            }
          }
        }

      )

    val bindingFuture = Http().newServerAt(uri, port).bind(route)
  }
}
