package de.htwg.se.stratego.model.fileIODatabase

import scala.concurrent.Future

trait FileIODatabaseInterface {

  def create(): Unit

  def update(id: Int, game: Future[String]) : Unit

  def delete() : Future[Any]

  def read(id: Int): Future[String]

}
