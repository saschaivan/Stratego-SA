package de.htwg.se.stratego.model.fileIODatabase

import de.htwg.se.stratego.model.fileIODatabase.fileIOSlick.Matchfield

import scala.collection.mutable.ListBuffer

trait FileIODatabaseInterface {

  def create(): Unit

  def update(id: Int, game: String) : Unit

  def delete() : Unit

  def read(id: Int): String

}
