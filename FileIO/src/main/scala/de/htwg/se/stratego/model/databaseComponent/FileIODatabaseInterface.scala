package de.htwg.se.stratego.model.databaseComponent

import scala.concurrent.Future

trait FileIODatabaseInterface {

  def update(id: Int, game: Future[String]) : Unit

  def delete() : Future[Any]

  def read(id: Int): Future[String]

}
