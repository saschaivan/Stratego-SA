package de.htwg.se.stratego.model.fileIoComponent

import spray.json.JsObject

trait FileIOInterface {
  def load_JSON: String
  def save(gamestate_json: String): Unit
}
