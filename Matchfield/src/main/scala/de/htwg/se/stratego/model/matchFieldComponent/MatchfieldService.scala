package de.htwg.se.stratego.model.matchFieldComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives.{complete, concat, get, path}
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField
import play.api.libs.json.{JsNumber, JsObject, Json}

case object MatchfieldService {

  def main(args: Array[String]): Unit = {

    val matchfield = new MatchField(4,4,false)

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.executionContext

    val route =
      concat (
        get {
          path("matchfield") {
            complete(matchfield.toString)
          }
        }
        /*
        post {
          path("json") {
            entity(as [String]) { game =>
              fileIO.save(game)
              println("GAME SAVED")
              complete("game saved")
            }
          }
        }
        */
      )

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
  }
}
