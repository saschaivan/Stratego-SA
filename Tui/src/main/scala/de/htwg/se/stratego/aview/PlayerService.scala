package de.htwg.se.stratego.aview

/*import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives._
import spray.json.{DefaultJsonProtocol, JsArray, JsValue, JsonFormat, RootJsonFormat, deserializationError, enrichAny}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.{Failure, Success}


trait JsonSupport extends DefaultJsonProtocol {

  implicit def listBufferFormat[T :JsonFormat] = new RootJsonFormat[ListBuffer[T]] {
    def write(listBuffer: ListBuffer[T]) = JsArray(listBuffer.map(_.toJson).toVector)

    def read(value: JsValue): ListBuffer[T] = value match {
      case JsArray(elements) => elements.map(_.convertTo[T])(collection.breakOut)
      case x => deserializationError("Expected ListBuffer as JsArray, but got " + x)
    }
  }

  implicit val figureval = jsonFormat2(Figure.FigureVal)
  implicit val gamecharacter = jsonFormat1(GameCharacter)
  implicit val playerFormat = jsonFormat2(Player)

}

case object PlayerService extends JsonSupport with SprayJsonSupport {

  implicit val system = ActorSystem(Behaviors.empty, "PlayerService")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.executionContext

  val cListSizeFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://localhost:8080/clistSize"))

  val playerNameFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://localhost:8080/playerName"))

  def clistSize: Int = {
    var clist = 0
    cListSizeFuture.onComplete {
      case Failure(_) => sys.error("HttpResponse failure")
      case Success(res) => {
        clist = res.entity.toString.toInt
      }
    }
    clist
  }

  def playerName: String = {
    var name = ""
    playerNameFuture.onComplete {
      case Failure(_) => sys.error("HttpResponse failure")
      case Success(res) => {
        name = res.entity.toString
      }
    }
    name
  }

  def main(args: Array[String]): Unit = {
    val route =
      concat (
        get {
          path("player") {
            entity(as[Player].map(Some(_)).recover(_ => _ => { case x => None })) { player: Option[Player] =>
              complete(Player("playerName", CharacterList(4).getCharacterList()))
            }
          }
        }
      )

    val bindingFuture = Http().newServerAt("localhost", 6060).bind(route)
  }
}*/