package de.htwg.se.stratego.controller.restController
import de.htwg.se.stratego.controller.{ControllerInterface, TuiService}

import scala.collection.mutable.ListBuffer

class RestController extends ControllerInterface {

  def controller: TuiService.type = TuiService

  override def handle(input: String): Unit = controller.sendGET_withQuery("handle", input)

  override def createEmptyMatchfield: Unit = controller.sendGET_noParam("createNewMatchfield")

  override def undo: Unit = controller.sendGET_noParam("undo")

  override def redo: Unit = controller.sendGET_noParam("redo")

  override def load: Unit = controller.sendGET_noParam("load")

  override def save: Unit = controller.sendGET_noParam("save")

  override def setPlayers(input: String): Unit = controller.sendGET_withQuery("handle", input)

  override def nextState: Unit = ???

  override def statusString: Unit = ???

  override def initMatchfield: Unit = ???

  override def attack(rowA: Int, colA: Int, rowD: Int, colD: Int): Unit = ???

  override def set(row: Int, col: Int, charac: String): Unit = ???

  override def move(dir: Char, row: Int, col: Int): Unit = ???

  override def matchFieldToString: Unit = ???

  override def nextPlayer: Unit = ???

  override def getSize: Unit = ???

  override def currentPlayerIndex: Unit = ???
}
