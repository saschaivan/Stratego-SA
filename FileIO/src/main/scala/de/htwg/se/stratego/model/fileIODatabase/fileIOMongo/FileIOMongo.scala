package de.htwg.se.stratego.model.fileIODatabase.fileIOMongo

import de.htwg.se.stratego.model.fileIODatabase.fileIODatabaseInterface
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala._
import org.mongodb.scala.model._
import org.mongodb.scala.model.UpdateOptions
import org.mongodb.scala.bson.BsonObjectId

import scala.collection.JavaConverters._

class FileIOMongo extends fileIODatabaseInterface {

  val mongoClient: MongoClient = MongoClient("mongodb://mongodb:27017")

  val database: MongoDatabase = mongoClient.getDatabase("mydb")

  val collection: MongoCollection[Document] = database.getCollection("FileIO")

  override def create(): Unit = {
    val doc: Document = Document("_id" -> 0, "player" -> "", "currentPlayerIndex" -> 0, "matchfield" -> "")
    collection.insertOne(doc)
  }

  override def update(game: String): Unit = {

  }

  override def delete(): Unit = ???

  override def read: String = ???

}
