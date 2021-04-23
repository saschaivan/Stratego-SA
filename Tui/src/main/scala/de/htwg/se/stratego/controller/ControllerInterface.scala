package de.htwg.se.stratego.controller

import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher {
  def handle(input:String):Unit
  def setPlayers(input:String):Unit
  def createEmptyMatchfield:Unit
  def initMatchfield:Unit
  def attack(rowA:Int, colA:Int, rowD:Int, colD:Int): Unit
  def set(row:Int, col:Int, charac:String):Unit
  def move(dir:Char, row:Int, col:Int):Unit
  def matchFieldToString:Unit
  def undo:Unit
  def redo:Unit
  def nextState:Unit
  def statusString:Unit
  def nextPlayer:Unit
  def getSize:Unit
  def currentPlayerIndex:Unit
  def load:Unit
  def save:Unit
}


abstract class PlayfieldEvent(field: String) extends Event {
  def playfield(): String = {
    field
  }
}

class NewGame(playfield: String) extends PlayfieldEvent(playfield)
class FieldChanged(playfield: String) extends PlayfieldEvent(playfield)
class PlayerChanged(playfield: String) extends PlayfieldEvent(playfield)
class MachtfieldInitialized(playfield: String) extends PlayfieldEvent(playfield)
class GameFinished(playfield: String) extends PlayfieldEvent(playfield)
class PlayerSwitch(playfield: String) extends PlayfieldEvent(playfield)



