package de.htwg.se.stratego.model.fileIODatabase

trait FileIODatabaseInterface {

  def create(): Unit

  def update(id: Int, game: String) : Unit

  def delete() : Unit

  def read(id: Int): String

}
