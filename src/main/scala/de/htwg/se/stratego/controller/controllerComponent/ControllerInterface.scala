package de.htwg.se.stratego.controller.controllerComponent

import de.htwg.se.stratego.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Field, Matrix, Player}

import scala.collection.mutable.ListBuffer
import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def handle(input:String): String
  def setPlayers(input:String): String
  def createEmptyMatchfield(size:Int): String
  def initMatchfield: String
  def attack(rowA:Int, colA:Int, rowD:Int, colD:Int): String
  def set(row:Int, col:Int, charac:String): String
  def move(dir:Char, row:Int, col:Int): String
  def matchFieldToString: String
  def undo: String
  def redo: String
  def nextState: Unit
  def statusString: String
  def nextPlayer: Int
  def getSize: Int
  def gameStatus: GameStatus
  def playerList: List[Player]
  def playerListBuffer: ListBuffer[Player]
  def currentPlayerIndex:Int
  def getField: Matrix[Field]
  def load: String
  def save: Unit
  def savedb: Unit
  def loaddb: Unit
  def deletedb: Unit
}

import scala.swing.event.Event

class NewGame extends Event
class FieldChanged extends Event
class PlayerChanged extends Event
class MachtfieldInitialized extends Event
class GameFinished extends Event
class PlayerSwitch extends Event
class LoadGame extends Event



