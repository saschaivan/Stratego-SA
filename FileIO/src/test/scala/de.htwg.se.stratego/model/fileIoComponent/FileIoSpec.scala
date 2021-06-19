package de.htwg.se.stratego.model.fileIo

import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.{FileIO => fileIOJson}
import de.htwg.se.stratego.model.fileIoComponent.fileIoXmlImpl.{FileIO => fileIOXml}
import org.scalatest.{Matchers, WordSpec}

class FileIoSpec extends WordSpec with Matchers {
  "A FileIO" when {
    "created" should {
      val fileIOJson = new fileIOJson
      val fileIOXml = new fileIOXml
      "load json" in {
        fileIOJson.save("(   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n,0,\"PlayerBlue PlayerRed\")")
        fileIOJson.load should be ("(   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n,0,\"PlayerBlue PlayerRed\")")
      }
      "load xml" in {
        fileIOXml.save("<matchField currentPlayerIndex=\"1\">\n    <field row=\"0\" col=\"0\" figName=\"9\" figValue=\"9\" colour=\"0\"> </field>\n    <field row=\"0\" col=\"2\" figName=\"6\" figValue=\"6\" colour=\"0\"> </field>\n    <field row=\"0\" col=\"3\" figName=\"F\" figValue=\"0\" colour=\"0\"> </field>\n    <field row=\"1\" col=\"1\" figName=\"8\" figValue=\"8\" colour=\"0\"> </field>\n    <field row=\"3\" col=\"0\" figName=\"9\" figValue=\"9\" colour=\"1\"> </field>\n    <field row=\"3\" col=\"1\" figName=\"8\" figValue=\"8\" colour=\"1\"> </field>\n    <field row=\"3\" col=\"2\" figName=\"6\" figValue=\"6\" colour=\"1\"> </field>\n    <field row=\"3\" col=\"3\" figName=\"F\" figValue=\"0\" colour=\"1\"> </field>\n</matchField>")
        fileIOXml.load should be ("<matchField currentPlayerIndex=\"1\">\n    <field row=\"0\" col=\"0\" figName=\"9\" figValue=\"9\" colour=\"0\"> </field>\n    <field row=\"0\" col=\"2\" figName=\"6\" figValue=\"6\" colour=\"0\"> </field>\n    <field row=\"0\" col=\"3\" figName=\"F\" figValue=\"0\" colour=\"0\"> </field>\n    <field row=\"1\" col=\"1\" figName=\"8\" figValue=\"8\" colour=\"0\"> </field>\n    <field row=\"3\" col=\"0\" figName=\"9\" figValue=\"9\" colour=\"1\"> </field>\n    <field row=\"3\" col=\"1\" figName=\"8\" figValue=\"8\" colour=\"1\"> </field>\n    <field row=\"3\" col=\"2\" figName=\"6\" figValue=\"6\" colour=\"1\"> </field>\n    <field row=\"3\" col=\"3\" figName=\"F\" figValue=\"0\" colour=\"1\"> </field>\n</matchField>")
      }
    }
  }
}