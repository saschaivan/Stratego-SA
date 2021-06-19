package de.htwg.se.stratego.model.databaseComponent.fileIOSlick

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

case class Matchfield(id: Int, row: Int, col: Int, figName: Option[String] = None, figValue: Option[Int] = None, colour: Option[Int] = None)

class SlickPlayer(tag: Tag) extends Table[(Int, Int, String, Int)](tag, "player") {

  def id: Rep[Int] = column[Int]("ID")
  def currentPlayerIndex: Rep[Int] = column[Int]("CPI")
  def players: Rep[String] = column[String]("players")
  def sizeOfMatchfield: Rep[Int] = column[Int]("sizeOfMatchfield")

  override def * : ProvenShape[(Int, Int, String, Int)] = (id, currentPlayerIndex, players, sizeOfMatchfield)
}

class SlickMatchfield(tag: Tag) extends Table[Matchfield](tag, "matchfield") {

  def id: Rep[Int] = column[Int]("ID")
  def row: Rep[Int] = column[Int]("ROW")
  def col: Rep[Int] = column[Int]("COL")
  def figName: Rep[Option[String]] = column[Option[String]]("FIGNAME")
  def figValue: Rep[Option[Int]] = column[Option[Int]]("FIGVALUE")
  def colour: Rep[Option[Int]] = column[Option[Int]]("COLOUR")

  override def * : ProvenShape[Matchfield] = (id, row, col, figName, figValue, colour) <>(Matchfield.tupled, Matchfield.unapply)
}
