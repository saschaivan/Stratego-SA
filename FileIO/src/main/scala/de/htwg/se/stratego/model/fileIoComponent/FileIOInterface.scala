package de.htwg.se.stratego.model.fileIoComponent

import spray.json.JsObject

trait FileIOInterface {
  def load: String
  def save(gamestate: String): Unit
}
