package de.htwg.se.stratego

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}

case object RootService {
  /*
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val route =
      concat (
        get {
          path("matchfield") {
            complete(HttpEntity(ContentTypes.`application/json`, )
          }
        }
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

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
  }
   */
}
