package de.htwg.se.stratego.controller.restController
import de.htwg.se.stratego.controller.{ControllerInterface, TuiService}

import scala.collection.mutable.ListBuffer

class RestController extends ControllerInterface {

  def controller: TuiService.type = TuiService

  override def handle(input: String): Unit = controller.POSTwithQuery("handle", input)

  override def createEmptyMatchfield(): Unit = controller.GETnoParam("createNewMatchfield")

  override def undo(): Unit = controller.GETnoParam("undo")

  override def redo(): Unit = controller.GETnoParam("redo")

  override def load(): Unit = controller.GETnoParam("load")

  override def save(): Unit = controller.GETnoParam("save")
}
