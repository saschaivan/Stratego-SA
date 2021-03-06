package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import scala.collection.mutable.ListBuffer

case class CharacterList(size: Int) {

  def getCharacterList(): ListBuffer[GameCharacter] = {
    size match {
      case 10 =>
        ListBuffer(GameCharacter(Figure.Scout),
          GameCharacter(Figure.Bomb),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Bomb),
          GameCharacter(Figure.Miner),
          GameCharacter(Figure.Bomb),
          GameCharacter(Figure.Sergeant),
          GameCharacter(Figure.General),
          GameCharacter(Figure.Sergeant),
          GameCharacter(Figure.Colonel),
          GameCharacter(Figure.Major),
          GameCharacter(Figure.Lieutenant),
          GameCharacter(Figure.Major),
          GameCharacter(Figure.Captain),
          GameCharacter(Figure.Lieutenant),
          GameCharacter(Figure.Bomb),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Captain),
          GameCharacter(Figure.Lieutenant),
          GameCharacter(Figure.Major),
          GameCharacter(Figure.Lieutenant),
          GameCharacter(Figure.Sergeant),
          GameCharacter(Figure.Bomb),
          GameCharacter(Figure.Marshal),
          GameCharacter(Figure.Sergeant),
          GameCharacter(Figure.Miner),
          GameCharacter(Figure.Miner),
          GameCharacter(Figure.Bomb),
          GameCharacter(Figure.Miner),
          GameCharacter(Figure.Miner),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Captain),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Captain),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Colonel),
          GameCharacter(Figure.Scout),
          GameCharacter(Figure.Spy),
          GameCharacter(Figure.Flag))

      case 9 => ListBuffer(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag))

      case 8 => ListBuffer(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag))

      case 7 => ListBuffer(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag))

      case 6 => ListBuffer(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag))

      case 5 => ListBuffer(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag))

      case 4 => ListBuffer(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag))
    }
  }
}
