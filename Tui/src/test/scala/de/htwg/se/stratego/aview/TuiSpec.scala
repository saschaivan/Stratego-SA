package de.htwg.se.stratego.aview
import de.htwg.se.stratego.controller.restController.RestController
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Stratego Tui" should {
    val controller = new RestController
    val tui = new Tui(controller)

    "create and empty Matchfield on input 'n'" in {
      tui.processInputLine("n")
    }
    "undo" in {
      tui.processInputLine("z")
    }
    "redo" in {
      tui.processInputLine("y")
    }
    "save" in {
      tui.processInputLine("s")
    }
    "load" in {
      tui.processInputLine("l")
    }
  }
}