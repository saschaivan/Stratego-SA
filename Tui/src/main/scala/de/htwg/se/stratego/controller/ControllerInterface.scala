package de.htwg.se.stratego.controller

import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher {
  def handle(input:String):Unit
  def createEmptyMatchfield():Unit
  def undo():Unit
  def redo():Unit
  def load():Unit
  def save():Unit
  def savedb(): Unit
}


abstract class MatchFieldEvent(field: String) extends Event {
  def matchfield(): String = {
    field
  }
}

class NewGame(matchfield: String) extends MatchFieldEvent(matchfield)
class FieldChanged(matchfield: String) extends MatchFieldEvent(matchfield)
class PlayerChanged(matchfield: String) extends MatchFieldEvent(matchfield)
class MachtfieldInitialized(matchfield: String) extends MatchFieldEvent(matchfield)
class GameFinished(matchfield: String) extends MatchFieldEvent(matchfield)
class PlayerSwitch(matchfield: String) extends MatchFieldEvent(matchfield)
class LoadGame(matchfield: String) extends MatchFieldEvent(matchfield)



