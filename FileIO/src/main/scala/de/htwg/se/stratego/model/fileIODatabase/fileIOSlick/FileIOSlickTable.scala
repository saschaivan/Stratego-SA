package de.htwg.se.stratego.model.FileIODatabase.fileIOSlick

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

//case class Matchfield(id: Int, row: Int, col: Int, figName: Option[String] = None, figValue: Option[Int] = None, colour: Option[Int] = None)

class SlickPlayer(tag: Tag) extends Table[(Int, Int, String)](tag, "player") {

  def id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def currentPlayerIndex: Rep[Int] = column[Int]("CPI")
  def players: Rep[String] = column[String]("players")

  override def * : ProvenShape[(Int, Int, String)] = (id, currentPlayerIndex, players)
}

class SlickMatchfield(tag: Tag) extends Table[(Int, Int, Int, Option[String], Option[Int], Option[Int])](tag, "matchfield") {

  def id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def row: Rep[Int] = column[Int]("ROW")
  def col: Rep[Int] = column[Int]("COL")
  def figName: Rep[Option[String]] = column[Option[String]]("FIGNAME")
  def figValue: Rep[Option[Int]] = column[Option[Int]]("FIGVALUE")
  def colour: Rep[Option[Int]] = column[Option[Int]]("COLOUR")

  override def * : ProvenShape[(Int, Int, Int, Option[String], Option[Int], Option[Int])] = (id, row, col, figName, figValue, colour)
}

class SlickOnlyOneTable(tag: Tag) extends Table[Int](tag, "TEST") {
  def id: Rep[Int] = column[Int]("ID")

  override def * : ProvenShape[Int] = (id)
}