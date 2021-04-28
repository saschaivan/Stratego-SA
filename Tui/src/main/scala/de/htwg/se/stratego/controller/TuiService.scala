package de.htwg.se.stratego.controller

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse, StatusCode, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, path, post}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.server.Directives._
import de.htwg.se.stratego.Tui.controller
import de.htwg.se.stratego.controller.TuiService.receivePOSTAndPublishEvent

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.swing.Reactor
import scala.swing.event.Event

object TuiService extends Reactor {

  listenTo(controller)

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "tui")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val uri = "tui_service"

  val port = 8082

  val rootUri = "root_service"

  val rootPort = 8080

  def receivePOSTAndPublishEvent(eventPath: String, event: String => Event): Route = {
    path("tui" / "events" / eventPath) {
      post {
        entity(as[String]) { matchfield =>
          controller.publish(event(matchfield))
          complete(HttpResponse.apply(StatusCode.int2StatusCode(200)))
        }
      }
    }
  }

  def start(): Future[Http.ServerBinding] = {
    Http().newServerAt(uri, port).bind(
      concat(
        receivePOSTAndPublishEvent("createNewMatchfield", field => new NewGame(field)),
        receivePOSTAndPublishEvent("fieldChanged", field => new FieldChanged(field)),
        receivePOSTAndPublishEvent("playerChanged", field => new PlayerChanged(field)),
        receivePOSTAndPublishEvent("machtfieldInitializied", field => new MachtfieldInitialized(field)),
        receivePOSTAndPublishEvent("gameFinished", field => new GameFinished(field)),
        receivePOSTAndPublishEvent("playerSwitched", field => new PlayerSwitch(field)),
        receivePOSTAndPublishEvent("loadGame", field => new PlayerSwitch(field))
      )
    )
  }

  def stop(server: Future[Http.ServerBinding]): Unit = {
    server
      .flatMap(_.unbind()).onComplete(_ => println(port + " released"))
  }

  def GETnoParam(commandPath: String): Unit = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"http://${rootUri}:${rootPort}/controller/" + commandPath,
      )
    )
  }

  def POSTwithQuery(commandPath: String, input: String): Unit = {
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://${rootUri}:${rootPort}/controller/" + commandPath,
        entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, input)
      )
    )
  }
}
