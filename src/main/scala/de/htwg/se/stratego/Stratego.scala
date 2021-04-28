package de.htwg.se.stratego

import com.google.inject.{Guice, Injector}
import de.htwg.se.stratego.aview.gui.PlayerFrame
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, RootService}

import scala.io.StdIn.readLine

object Stratego {

  val injector: Injector = Guice.createInjector(new StrategoModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val gui = new PlayerFrame(controller)
  val rootService = RootService

  def main(args: Array[String]): Unit = {
    val start = rootService.server()
    var input = ""
    do {
      input = readLine()
    } while (!input.equals("q"))
    rootService.stop(start)
  }
}
