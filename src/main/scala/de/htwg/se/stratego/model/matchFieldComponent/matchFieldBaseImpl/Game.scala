package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.playerComponent.Player

case class Game(playerA: Player, playerB: Player, size: Int, matchField: MatchFieldInterface) {
  val bList: Seq[GameCharacter] = playerA.characterList
  val rList: Seq[GameCharacter] = playerB.characterList

  /*def init(currentMatchField: MatchFieldInterface): Game = {
    var bIdx = 0
    var rIdx = 0
    var field: MatchFieldInterface = matchField
    for {col <- 0 until matchField.fields.matrixSize
         row <- 0 until matchField.fields.matrixSize} {
      if (currentMatchField.fields.field(row, col).isSet) {
        field = currentMatchField
      }
      if (isBlueField(row)) {
        field = field.addChar(row, col, bList(bIdx), Colour.FigureCol(0))
        bIdx += 1
      } else if (isRedField(row)) {
        field = field.addChar(row, col, rList(rIdx), Colour.FigureCol(1))
        rIdx += 1
      } else {

      }
    }
    copy(matchField = field)
  }*/

  def init(currentMatchField: MatchFieldInterface, row: Int, col: Int, bIdx: Int, rIdx: Int): Game = {
    var field: MatchFieldInterface = currentMatchField
    if (row < size) {
      if (currentMatchField.fields.field(row, col).isSet) {
        field = currentMatchField
      }
      if (isBlueField(row)) {
        field = field.addChar(row, col, bList(bIdx), Colour.FigureCol(0))
        if (col < size-1) {
          return init(field, row, col + 1, bIdx + 1, rIdx)
        }
        else {
          return init(field, row + 1, 0, bIdx + 1, rIdx)
        }
      } else if (isRedField(row)) {
        field = field.addChar(row, col, rList(rIdx), Colour.FigureCol(1))
        if (col < size-1) {
          return init(field, row, col + 1, bIdx, rIdx + 1)
        }
        else {
          return init(field, row + 1, 0, bIdx, rIdx + 1)
        }
      } else {
        return init(field, row + 1, col, bIdx, rIdx)
      }
    }
    copy(matchField = field)
  }

  def characValue(charac: String): Int = {
    if (charac.matches("[1-9]")) {
      return charac.toInt
    }
    charac match {
      case "B" => 11
      case "M" => 10
      case "F" => 0
    }
  }

  def isBlueField(row: Int): Boolean = {
    matchField.fields.matrixSize match {
      case 4 | 5 => if (row > 0) false else true
      case 6 | 7 => if (row > 1) false else true
      case 8 | 9 => if (row > 2) false else true
      case 10 => if (row > 3) false else true
    }
  }

  def isRedField(row: Int): Boolean = {
    matchField.fields.matrixSize match {
      case 4 => if (row < 3) false else true
      case 5 | 6 => if (row < 4) false else true
      case 7 | 8 => if (row < 5) false else true
      case 9 | 10 => if (row < 6) false else true
    }
  }

  /*def setBlue(row:Int, col:Int, charac: String): MatchFieldInterface = {
    if (isBlueChar(charac) && isBlueField(row) && !matchField.fields.field(row,col).isSet) {
      val idx = bList.indexOf(GameCharacter(Figure.FigureVal(charac,characValue(charac))))
      matchField.addChar(row,col,bList(idx),Colour.FigureCol(0))
      bList.patch(idx, Nil, 1)
      return matchField
    }
    matchField
  }*/

  def setBlue(row: Int, col: Int, charac: String): MatchFieldInterface = {
    setRed(bList, Colour.FigureCol(0))(row, col, charac)
  }


  // (color: Colour)
  // (color: FigureCol)
  // color: Colour.FigureCol
  /*def isBlueChar(charac:String): Boolean = {
    bList.foreach(GameCharacter =>
      if(GameCharacter.toString().equals(charac)) {
        return true
      }
    )
    false
  }*/

  def isBlueChar(charac: String): Boolean = {
    isRedChar(bList)(charac)
  }

  def set(player: Int, row: Int, col: Int, charac: String): MatchFieldInterface = {
    player match {
      case 0 => return setBlue(row, col, charac)
      case 1 => return setRed(rList, Colour.FigureCol(1))(row, col, charac)
    }
    matchField
  }

  // TODO parameter direkt übergeben und statt FigureCol(1) RED setzen
  def setRed(seq: Seq[GameCharacter], colour: Colour.FigureCol)(row: Int, col: Int, charac: String): MatchFieldInterface = {
    if (isRedChar(rList)(charac) && isRedField(row) && !matchField.fields.field(row, col).isSet) {
      val idx = rList.indexOf(GameCharacter(Figure.FigureVal(charac, characValue(charac))))
      matchField.addChar(row, col, rList(idx), Colour.FigureCol(1))
      rList.patch(idx, Nil, 1)
      return matchField
    }
    matchField
  }

  def isRedChar(seq: Seq[GameCharacter])(charac: String): Boolean = {
    rList.map(GameCharacter => if (GameCharacter.figure.name.equals(charac)) return true else false)
    false
  }

  def onlyBombAndFlag(board: MatchFieldInterface, currentPlayerIndex: Int, row:Int, col:Int): Boolean = {
    if(row < size) {
        if (board.fields.field(row, col).isSet && board.fields.field(row, col).colour.get.value == currentPlayerIndex) {
          if (board.fields.field(row, col).character.get.figure.value == 1 ||
            board.fields.field(row, col).character.get.figure.value == 2 ||
            board.fields.field(row, col).character.get.figure.value == 3 ||
            board.fields.field(row, col).character.get.figure.value == 4 ||
            board.fields.field(row, col).character.get.figure.value == 5 ||
            board.fields.field(row, col).character.get.figure.value == 6 ||
            board.fields.field(row, col).character.get.figure.value == 7 ||
            board.fields.field(row, col).character.get.figure.value == 8 ||
            board.fields.field(row, col).character.get.figure.value == 9 ||
            board.fields.field(row, col).character.get.figure.value == 10) {
              return false
          }
      }
      if (col < size-1) {
        return onlyBombAndFlag(board, currentPlayerIndex, row, col + 1)
      }
      else {
        return onlyBombAndFlag(board, currentPlayerIndex, row + 1, 0)
      }
    }
    true
  }

  /*def onlyBombAndFlag(board: MatchFieldInterface, currentPlayerIndex: Int): Boolean = {
    for {row <- 0 until board.fields.matrixSize
         col <- 0 until board.fields.matrixSize} {
      if (board.fields.field(row, col).isSet && board.fields.field(row, col).colour.get.value == currentPlayerIndex) {
        if (board.fields.field(row, col).character.get.figure.value == 1 ||
          board.fields.field(row, col).character.get.figure.value == 2 ||
          board.fields.field(row, col).character.get.figure.value == 3 ||
          board.fields.field(row, col).character.get.figure.value == 4 ||
          board.fields.field(row, col).character.get.figure.value == 5 ||
          board.fields.field(row, col).character.get.figure.value == 6 ||
          board.fields.field(row, col).character.get.figure.value == 7 ||
          board.fields.field(row, col).character.get.figure.value == 8 ||
          board.fields.field(row, col).character.get.figure.value == 9 ||
          board.fields.field(row, col).character.get.figure.value == 10) {
          return false
        }
      }
    }
    true
  }*/

  def flagFound(rowD: Int, colD: Int, rowA: Int, colA: Int, currentPlayerIndex: Int): Boolean = {
    if (rowD <= this.matchField.fields.matrixSize - 1 && rowD >= 0 && colD >= 0 && colD <= this.matchField.fields.matrixSize - 1 &&
      this.matchField.fields.field(rowA, colA).isSet.equals(true) && this.matchField.fields.field(rowD, colD).isSet.equals(true)
      && this.matchField.fields.field(rowD, colD).colour.get.value != currentPlayerIndex &&
      this.matchField.fields.field(rowD, colD).character.get.figure.value == 0) true else false
  }

  def attackValid(rowD: Int, colD: Int, rowA: Int, colA: Int, currentPlayerIndex: Int): Boolean = {
    if (rowD <= this.matchField.fields.matrixSize - 1 && rowD >= 0 && colD >= 0 && colD <= this.matchField.fields.matrixSize - 1 &&
      this.matchField.fields.field(rowA, colA).isSet && this.matchField.fields.field(rowA, colA).colour.get.value == currentPlayerIndex
      && this.matchField.fields.field(rowD, colD).isSet && this.matchField.fields.field(rowD, colD).colour.get.value != currentPlayerIndex
      && !isFlagOrBomb(matchField, rowA, colA))
      true else false
  }


  def move(direction: Char, matchField: MatchFieldInterface, row: Int, col: Int, currentPlayerIndex: Int): MatchFieldInterface = {
    if (matchField.fields.field(row, col).isSet.equals(true) && matchField.fields.field(row, col).colour.get.value == currentPlayerIndex) {
      direction match {
        case 'u' => return moveUp(matchField, row, col)
        case 'd' => return moveDown(matchField, row, col)
        case 'r' => return moveRight(matchField, row, col)
        case 'l' => return moveLeft(matchField, row, col)
      }
    }
    matchField
  }

  def moveDirection(rowCol:Int, eqRowCol:Int, rowPos:Int, colPos:Int, newRowPos: Int, newColPos:Int)(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (rowCol == eqRowCol || matchField.fields.field(rowPos, colPos).isSet.equals(true) || isFlagOrBomb(matchField, row, col)) {
      matchField
    } else {
      matchField.removeChar(row, col).addChar(newRowPos, newColPos, matchField.fields.field(row, col).character.get, matchField.fields.field(row, col).colour.get)
    }
  }

  def moveDown(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    moveDirection(row, size-1, row+1, col, row+1, col)(matchField, row, col)
  }

  def moveUp(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    moveDirection(row, 0, row-1, col, row-1, col)(matchField, row, col)
  }

  def moveLeft(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    moveDirection(col, 0, row, col-1, row, col-1)(matchField, row, col)
  }

  def moveRight(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    moveDirection(col, size-1, row, col+1, row, col+1)(matchField, row,col)
  }


  // TODO alle move Befehle auch mit currying anpassen
  /*def moveDown(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (row == size - 1 || matchField.fields.field(row + 1, col).isSet.equals(true) || isFlagOrBomb(matchField, row, col)) {
      matchField
    } else {
      matchField.removeChar(row, col).addChar(row + 1, col, matchField.fields.field(row, col).character.get, matchField.fields.field(row, col).colour.get)
    }
  }

  def moveUp(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (row == 0 || matchField.fields.field(row - 1, col).isSet.equals(true) || isFlagOrBomb(matchField, row, col)) {
      matchField
    } else {
      matchField.removeChar(row, col).addChar(row - 1, col, matchField.fields.field(row, col).character.get, matchField.fields.field(row, col).colour.get)
    }
  }

  def moveLeft(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (col == 0 || matchField.fields.field(row, col - 1).isSet.equals(true) || isFlagOrBomb(matchField, row, col)) {
      matchField
    } else {
      matchField.removeChar(row, col).addChar(row, col - 1, matchField.fields.field(row, col).character.get, matchField.fields.field(row, col).colour.get)
    }
  }

  def moveRight(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (col == size - 1 || matchField.fields.field(row, col + 1).isSet.equals(true) || isFlagOrBomb(matchField, row, col)) {
      matchField
    } else {
      matchField.removeChar(row, col).addChar(row, col + 1, matchField.fields.field(row, col).character.get, matchField.fields.field(row, col).colour.get)
    }
  }*/

  def figureHasValue(matchF: MatchFieldInterface, row: Int, col: Int): Int = matchF.fields.field(row, col).character.get.figure.value

  def isFlagOrBomb(matchField: MatchFieldInterface, row: Int, col: Int): Boolean = if (matchField.fields.field(row, col).character.get.figure.value == 0 ||
    matchField.fields.field(row, col).character.get.figure.value == 11) true else false

  object Context extends Game(playerA: Player, playerB: Player, size: Int, matchField: MatchFieldInterface) {
    def attack(matchField: MatchFieldInterface, rowA: Int, colA: Int, rowD: Int, colD: Int, currentPlayerIndex: Int): MatchFieldInterface = {
      def strategy1: MatchFieldInterface = matchField

      def strategy3: MatchFieldInterface = matchField.removeChar(rowD, colD).addChar(rowD, colD,
        matchField.fields.field(rowA, colA).character.get, matchField.fields.field(rowA, colA).colour.get).removeChar(rowA, colA)

      def strategy6: MatchFieldInterface = matchField.removeChar(rowD, colD)

      def strategy7: MatchFieldInterface = matchField.removeChar(rowA, colA)

      def strategy8: MatchFieldInterface = matchField.removeChar(rowA, colA).removeChar(rowD, colD)

      // is field set
      if (matchField.fields.field(rowA, colA).isSet.equals(false) || matchField.fields.field(rowD, colD).isSet.equals(false)) return strategy1
      // is attack valid
      if (matchField.fields.field(rowD, colD).colour.get.value == currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value == currentPlayerIndex) return strategy1
      // is enemy attack invalid
      if (matchField.fields.field(rowD, colD).colour.get.value != currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value != currentPlayerIndex) return strategy1
      // is player attack wrong
      if (matchField.fields.field(rowD, colD).colour.get.value == currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value != currentPlayerIndex) return strategy1
      // is attack far away
      if (((Math.abs(rowA - rowD) > 1) || (Math.abs(colA - colD) > 1)) || ((Math.abs(rowA - rowD) == 1) && (Math.abs(colA - colD) == 1))) return strategy1
      // does the miner attack bomb
      if (figureHasValue(matchField, rowA, colA) == 3 && figureHasValue(matchField, rowD, colD) == 11) return strategy6
      // does the spy attack marshal
      if ((figureHasValue(matchField, rowA, colA) == 1) && (figureHasValue(matchField, rowD, colD) == 10)) return strategy3
      // is defense stronger
      if (figureHasValue(matchField, rowA, colA) < figureHasValue(matchField, rowD, colD)) return strategy7
      // is attack stronger
      if (figureHasValue(matchField, rowA, colA) > figureHasValue(matchField, rowD, colD)) return strategy3
      // does attack equal defense
      if (figureHasValue(matchField, rowA, colA) == figureHasValue(matchField, rowD, colD)) return strategy8
      matchField
    }
  }

}
