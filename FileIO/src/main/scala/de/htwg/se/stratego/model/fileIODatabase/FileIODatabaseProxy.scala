package de.htwg.se.stratego.model.fileIODatabase

import com.google.inject.Guice

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class FileIODatabaseProxy {

  val injector = Guice.createInjector(new FileIOModule)
  val db = injector.getInstance(classOf[FileIODatabaseInterface])

  def create(): Unit = db.create

  def update(id: Int, game: String): Unit = db.update(id, game)

  def delete(): Unit = db.delete()

  def read(id: Int): Future[String] = db.read(id)
}
