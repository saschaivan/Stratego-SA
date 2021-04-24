package de.htwg.se.stratego.aview

import de.htwg.se.stratego
import de.htwg.se.stratego.controller.ControllerInterface

import scala.swing.Reactor
import scala.sys.exit

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => System.exit(0)
      case "n" => controller.createEmptyMatchfield()
      case "z" => controller.undo()
      case "y" => controller.redo()
      case "s" => controller.save()
      case "l" => controller.load()
      case _ => controller.handle(input)
    }
  }

  reactions +={
    case event: stratego.controller.FieldChanged => print(event.matchfield())
    case event: stratego.controller.PlayerChanged => print(event.matchfield())
    case event: stratego.controller.MachtfieldInitialized => print(event.matchfield())
    case event: stratego.controller.NewGame => print(event.matchfield())
    case event: stratego.controller.GameFinished => print(event.matchfield())
    case event: stratego.controller.PlayerSwitch => print(event.matchfield())
  }
}
