package de.htwg.se.stratego.controller.controllerComponent

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.server.Directives._
import de.htwg.se.stratego.Stratego.controller
import de.htwg.se.stratego.aview.gui.GameFrame

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.swing.Reactor

object RootService extends Reactor {

  listenTo(controller)

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "root")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val uri = "root_service"

  val tuiUri = "tui_service"

  val port = 8080

  def server(): Future[Http.ServerBinding] = {
    val route =
      concat(
        path("controller" / "createNewMatchfield") {
          get {
            controller.createEmptyMatchfield(controller.getSize)
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, GameStatus.getMessage(controller.gameStatus)))
          }
        },
        get {
          path("controller" / "load") {
            controller.load
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, GameStatus.getMessage(controller.gameStatus)))
          }
        },
        get {
          path("controller" / "save") {
            controller.save
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, GameStatus.getMessage(controller.gameStatus)))
          }
        },
        get {
          path("controller" / "redo") {
            controller.redo
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, GameStatus.getMessage(controller.gameStatus)))
          }
        },
        path("controller" / "handle") {
          entity(as[String]) {
            input =>
              post {
                controller.handle(input)
                complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, GameStatus.getMessage(controller.gameStatus)))
              }
          }
        },
      )
    Http().newServerAt(uri, port).bind(route)
  }

  reactions += {
    case event: NewGame => postEvent("createNewMatchfield", controller.matchFieldToString + "\nCreated new matchfield\nPlease enter the names like (player1 player2)\n")
    case event: FieldChanged => postEvent("fieldChanged", controller.matchFieldToString)
    case event: PlayerChanged => postEvent("playerChanged", "Hello " + controller.playerList(0) + " and " + controller.playerList(1) + "!\n")
    case event: MachtfieldInitialized => postEvent("machtfieldInitializied", "Matchfield initialized\n")
    case event: GameFinished => postEvent("gameFinished", "Game finished! " + controller.playerList(controller.currentPlayerIndex) + " has won the game!\n")
    case event: PlayerSwitch => postEvent("playerSwitched", controller.playerList(controller.currentPlayerIndex) + " it's youre turn!\n")
    case event: LoadGame => postEvent("loadGame", "Game loaded\n")
  }

  def stop(server: Future[Http.ServerBinding]): Unit = {
    server
      .flatMap(_.unbind()).onComplete(_ => println(port + " released"))
  }

  def postEvent(event: String, output: String): Unit = {
    println(event)
    Http().singleRequest(
      HttpRequest(
        method = HttpMethods.POST,
        uri = s"http://${tuiUri}:8082/tui/events/" + event,
        entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, output)
      )
    )
  }
}
