package de.htwg.se.stratego.model.fileIODatabase.fileIOMongo

import scala.util.{Failure, Success, Try}
import de.htwg.se.stratego.model.fileIODatabase.FileIODatabaseInterface
import org.mongodb.scala._
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.concurrent.duration.{Duration, DurationInt}
import scala.util.Try


class FileIOMongo extends FileIODatabaseInterface {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  val connectionString: String = "mongodb://root:rootpassword@mongodb:27017/?authSource=admin"
  val mongoClient: MongoClient = MongoClient(connectionString)
  val database: MongoDatabase = mongoClient.getDatabase("Mongodb")
  val collection: MongoCollection[Document] = database.getCollection("FileIO")

  override def update(id: Int, game: Future[String]): Unit = {
    delete()
    game.onComplete((value: Try[String]) => {
      value match {
        case Success(value) => {
          val doc: Document = Document("_id" -> id, "game" -> value)
          observerInsertion(collection.insertOne(doc))
        }
        case Failure(e) => println("game is not available: " + e)
      }
    })

  }

  override def delete(): Future[Any] = {
    collection.deleteOne(equal("_id", 1)).toFuture()
  }

  override def read(id: Int): Future[String] = {
    val resultgame: Document = Await.result(collection.find(equal("_id", id)).first().head(), atMost = 10.second)
    val game: Future[String] = Future(resultgame("game").asString().getValue)
    game
  }

  private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit = {
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = println(s"inserted: $result")

      override def onError(e: Throwable): Unit = println(s"onError: $e")

      override def onComplete(): Unit = println("completed")
    })
  }


  override def create(): Unit = ???

}
