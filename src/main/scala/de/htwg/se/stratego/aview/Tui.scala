package de.htwg.se.stratego.aview

import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, MachtfieldInitialized, NewGame, PlayerChanged, PlayerSwitch}

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)
  val size = controller.getSize

  def processInputLine(input: String):String = {
    input match {
      case "q" =>"Bye bye!"
      case "n" => controller.createEmptyMatchfield(size)
      case "z" => controller.undo
      case "y" => controller.redo
      case "s" => controller.save
      case "l" => controller.load
      case _ => controller.handle(input)
    }
  }

  reactions +={
    case event: FieldChanged => printTui
    case event: PlayerChanged => println("Hello " + controller.playerList(0) + " and "+ controller.playerList(1) + "!")
    case event: MachtfieldInitialized => println("Matchfield initialized")
    case event: NewGame =>
      printTui
      println("Created new matchfield\nPlease enter the names like (player1 player2)")
    case event: GameFinished => println("Game finished! " + controller.playerList(controller.currentPlayerIndex) + " has won the game!")
    case event: PlayerSwitch => println(controller.playerList(controller.currentPlayerIndex) + " it's youre turn!")
  }

  def printTui: Unit = {
    println(controller.matchFieldToString)
    println(GameStatus.getMessage(controller.gameStatus))
  }

}
