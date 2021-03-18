package de.htwg.se.stratego.model.playerComponent

import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.GameCharacter

case class Player(name: String, characterList: Seq[GameCharacter]) {
  override def toString: String = name
}
