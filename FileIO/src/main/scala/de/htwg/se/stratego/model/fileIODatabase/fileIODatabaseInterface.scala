package de.htwg.se.stratego.model.FileIODatabase

import de.htwg.se.stratego.model.FileIODatabase.fileIOSlick.Matchfield

import scala.collection.mutable.ListBuffer

trait fileIODatabaseInterface {

  def update(game: String) : Unit

  def delete() : Unit

  def readMatchfield: String

}
