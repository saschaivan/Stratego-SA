package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface

case class MatchField @Inject() (fields: Matrix[Field]) extends MatchFieldInterface {

  def this (rowSize: Int, colSize: Int, isSet: Boolean) = this(new Matrix[Field](rowSize, colSize, Field(isSet)))

  def addChar(row: Int, col: Int, char: GameCharacter, colour: Colour.FigureCol): MatchField = copy(fields.updateField(row, col, Field(true, Some(char), Some(colour))))

  def removeChar(row: Int, col: Int): MatchField = copy(fields.updateField(row, col, Field(false,None,None)))

  def legend():String = {
    val welcome = "**********  STRATEGO  **********\n\n"
    val n = "n:   create a new empty machtfield\n"
    val z = "z:   undo\n"
    val y = "y:   redo\n"
    val q = "q:   quit the programm\n"
    welcome + n + z + y + q
  }

  def frame(row:Int): String = {
    val plus = "+"
    val line = "-"
    val combine = (plus + line * 5) * row + plus
    combine
  }

  def createNewMatchField: MatchFieldInterface = new MatchField(fields.matrixSize,fields.matrixSize,false)

  override def toString:String = {
    val col = fields.matrixSize
    val row = fields.matrixSize
    val n = fields.matrixSize - 1
    val pipe = "|"
    val new_line = "\n"
    var matchField = ""

    for(rowNumbers <- 0 until row) matchField += "   " + rowNumbers + "  "
    matchField += new_line + frame(fields.matrixSize) + new_line
    for { row <- 0 until row
          col <- 0 until col }
    {
      if (fields.field(row, col).isSet) {
        if (fields.field(row,col).colour.get.value==0) {
          matchField += "|  " + "\033[0;34m" + fields.field(row,col) + Console.RESET + "  "
        } else {
          matchField += "|  " + "\033[0;31m" + fields.field(row,col) + Console.RESET + "  "
        }
      } else {
        matchField += "|     "
      }
      if (col == n) {
        matchField += pipe + " " + row + new_line
        matchField += frame(fields.matrixSize) + new_line
      }
    }
    matchField += legend()
    matchField
  }

}
