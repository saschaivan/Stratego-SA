package de.htwg.se.stratego.model.fileIODatabase

trait fileIODatabaseInterface {

  def create() : Unit

  def update(game: String) : Unit

  def delete() : Unit

  def readPlayer(resid: Int): String

  def readMatchfield(resid: Int): String

}
