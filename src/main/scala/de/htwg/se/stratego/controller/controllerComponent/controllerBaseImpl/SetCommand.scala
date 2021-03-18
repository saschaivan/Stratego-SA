package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.stratego.util.Command

class SetCommand(player: Int, row:Int, col: Int, charac: String, controller: Controller) extends Command {
  override def doStep: Unit =   controller.matchField = controller.game.set(player, row, col, charac)

  override def undoStep: Unit = controller.matchField = controller.matchField.removeChar(row, col)

  override def redoStep: Unit = controller.matchField = controller.game.set(player, row, col, charac)
}
