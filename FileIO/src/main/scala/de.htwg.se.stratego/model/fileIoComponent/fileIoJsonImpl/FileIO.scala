package de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import javax.swing.JOptionPane

import scala.io.Source
import scala.util.control.Breaks.break
import scala.util.{Failure, Success, Try}



class FileIO extends FileIOInterface {

  def load_JSON: String = {
    fileNotFound("matchField.json") match {
      case Success(v) => println("File Found")
      case Failure(v) => JOptionPane.showMessageDialog(null, "Es ist kein Spielstand vorhanden!")
        break
    }
    val file = Source.fromFile("matchField.json")
    try file.mkString finally file.close()
  }

  def fileNotFound(filename: String): Try[String] = {
    Try(Source.fromFile(filename).getLines().mkString)
  }

  def save(gamestate_json: String): Unit = {
    import java.io._
    val print_writer = new PrintWriter(new File("matchField.json"))
    print_writer.write(gamestate_json)
    print_writer.close()
  }

}






