package de.htwg.se.stratego.model.databaseComponent

import com.google.inject.Guice

import scala.concurrent.{Await, Future}
import de.htwg.se.stratego.model.FileIOModule

class FileIODatabaseProxy extends FileIODatabaseInterface {

  val injector = Guice.createInjector(new FileIOModule)
  val db = injector.getInstance(classOf[FileIODatabaseInterface])


  def update(id: Int, game: Future[String]): Unit = db.update(id, game)

  def delete(): Future[Any] = db.delete()

  def read(id: Int): Future[String] = db.read(id)
}
