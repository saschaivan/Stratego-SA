package de.htwg.se.stratego

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.controller.{ControllerInterface, TuiService}
import de.htwg.se.stratego.controller.restController.RestController

object Tui {
  val controller: ControllerInterface = new RestController
  val tui = new Tui(controller)
  val restController: TuiService.type = TuiService

  def main(args: Array[String]): Unit = {

    val server = restController.start()

    var input: String = ""
    println("Spielernamen im Format \"Spieler1 Spieler2\" eingeben")
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")

    restController.stop(server)

  }
}
