package de.htwg.se.stratego.model.fileIODatabase

import de.htwg.se.stratego.model.fileIODatabase.fileIOSlick.Matchfield

import scala.collection.mutable.ListBuffer

trait fileIODatabaseInterface {

  def create(): Unit

  def update(game: String) : Unit

  def delete() : Unit

  def read: String

}
