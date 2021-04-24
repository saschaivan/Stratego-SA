package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import scala.collection.mutable.ListBuffer

case class Player(name: String, characterList: ListBuffer[GameCharacter]) {
  override def toString: String = name
}
