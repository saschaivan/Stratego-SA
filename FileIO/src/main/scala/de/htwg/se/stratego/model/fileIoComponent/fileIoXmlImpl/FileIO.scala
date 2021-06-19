package de.htwg.se.stratego.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.FileIOModule

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {

  override def load: String = {
    val file: Elem = scala.xml.XML.loadFile("playfield.xml")
    file.toString()
  }

  override def save(gamestate: String): Unit = {
    val elem = scala.xml.XML.loadString(gamestate)
    scala.xml.XML.save("playfield.xml", elem)
  }

}
