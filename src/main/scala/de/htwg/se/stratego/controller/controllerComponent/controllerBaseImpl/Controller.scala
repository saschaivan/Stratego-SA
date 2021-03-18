package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.stratego.StrategoModule
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, MachtfieldInitialized, NewGame, PlayerChanged, PlayerSwitch}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Field, Game, MatchField, Matrix}
import de.htwg.se.stratego.model.playerComponent.Player
import de.htwg.se.stratego.util.UndoManager

import scala.swing.Publisher


class Controller @Inject()(var matchField:MatchFieldInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new StrategoModule)
  val fileIO = injector.getInstance(classOf[FileIOInterface])

  val list = CharacterList(matchField.fields.matrixSize)
  var playerBlue = Player("PlayerBlue", list.getCharacterList())
  var playerRed = Player("PlayerRed", list.getCharacterList())
  var game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)
  var playerList = List[Player](playerBlue,playerRed)

  var gameStatus: GameStatus = IDLE
  var currentPlayerIndex: Int = 0
  private val undoManager = new UndoManager
  var state: ControllerState = EnterPlayer(this)

  def handle(input: String):String = {
    state.handle(input)
  }

  def welcome():String = {
    "Welcome to STRATEGO! " +
      "Please enter first name of Player1 and then of Player2 like (player1 player2)!"
  }

  def setPlayers(input: String): String = {
    playerList = game.setPlayers(input)
    nextState
    publish(new PlayerChanged)
    ""
  }

  def createEmptyMatchfield(size:Int): String = {
    matchField = new MatchField(size, size, false)
    game = game.copy(playerBlue,playerRed,size,matchField)
    gameStatus=NEW
    state = EnterPlayer(this)
    publish(new NewGame)
    currentPlayerIndex=0
    "created new matchfield\nPlease enter the names like (player1 player2)"
  }

  def initMatchfield(): String = {
    var newMatchField = matchField
    newMatchField = game.init(matchField)
    if (matchField.equals(newMatchField)) {
      ""
    } else {
      matchField = game.init(matchField)
      gameStatus=INIT
      nextState
      publish(new MachtfieldInitialized)
      playerList(currentPlayerIndex) + " it's your turn!"
    }
  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): String ={
    if(game.onlyBombAndFlag(matchField,currentPlayerIndex) && matchField.fields.field(rowA,colA).isSet &&
      matchField.fields.field(rowA,colA).colour.get.value==currentPlayerIndex) {
      currentPlayerIndex = nextPlayer
      publish(new GameFinished)
      currentPlayerIndex=1
      nextState
      createEmptyMatchfield(matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if(rowD <= matchField.fields.matrixSize - 1 && rowD >= 0 && colD >= 0 && colD <= matchField.fields.matrixSize - 1 &&
      matchField.fields.field(rowA, colA).isSet.equals(true) && matchField.fields.field(rowD, colD).isSet.equals(true)
      && matchField.fields.field(rowD,colD).colour.get.value!= currentPlayerIndex &&
      matchField.fields.field(rowD,colD).character.get.figure.value==0){
      publish(new GameFinished)
      currentPlayerIndex=1
      nextState
      createEmptyMatchfield(matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if (rowD <= matchField.fields.matrixSize - 1 && rowD >= 0 && colD >= 0 && colD <= matchField.fields.matrixSize - 1 &&
      matchField.fields.field(rowA,colA).isSet && matchField.fields.field(rowA,colA).colour.get.value==currentPlayerIndex
      && matchField.fields.field(rowD,colD).isSet && matchField.fields.field(rowD,colD).colour.get.value!= currentPlayerIndex) {
      matchField = game.Context.attack(matchField, rowA, colA, rowD, colD,currentPlayerIndex)
      gameStatus = ATTACK
      currentPlayerIndex= nextPlayer
      publish(new PlayerSwitch)
      publish(new FieldChanged)
    }
    ""
  }

  def set(row:Int, col:Int, charac:String): String = {
    currentPlayerIndex match {
      case 0 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.bList.size == 0){
          currentPlayerIndex=nextPlayer
          publish(new PlayerSwitch)
        }
      case 1 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.rList.size == 0){
          currentPlayerIndex=nextPlayer
          nextState
          publish(new MachtfieldInitialized)
        }
    }
    publish(new FieldChanged)
    if(game.rList.size == 0){
        return "Move Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\n" +
        playerList(currentPlayerIndex) + " it's your turn!"
    }
    if(game.bList.size == 0){
      return ""
    }
    ""
  }

  def move(dir: Char, row:Int, col:Int): String = {
    if (matchField.fields.field(row,col).isSet && matchField.fields.field(row,col).colour.get.value==currentPlayerIndex) {
      if(game.onlyBombAndFlag(matchField,currentPlayerIndex)) {
        currentPlayerIndex = nextPlayer
        publish(new GameFinished)
        currentPlayerIndex=1
        nextState
        createEmptyMatchfield(matchField.fields.matrixSize)
        return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
          "Game finished! Play new Game with (n)!"
      }
      undoManager.doStep(new MoveCommand(dir, matchField, row, col, currentPlayerIndex, this))
      if (!matchField.fields.field(row,col).isSet) {
        currentPlayerIndex = nextPlayer
        publish(new FieldChanged)
        publish(new PlayerSwitch)
      }
    }
    ""
  }

  def matchFieldToString: String = matchField.toString

  def undo: String = {
    currentPlayerIndex= nextPlayer
    undoManager.undoStep
    gameStatus = UNDO
    publish(new FieldChanged)
    publish(new PlayerSwitch)
    "undo"
  }

  def redo: String = {
    currentPlayerIndex=nextPlayer
    undoManager.redoStep
    gameStatus = REDO
    publish(new FieldChanged)
    publish(new PlayerSwitch)
    "redo"
  }

  def nextState: Unit = {
    state = state.nextState()
    publish(new FieldChanged)
  }

  def statusString:String = GameStatus.getMessage(gameStatus)

  def nextPlayer: Int = if (currentPlayerIndex == 0) 1 else 0

  override def getSize: Int = matchField.fields.matrixSize

  override def getField: Matrix[Field] = matchField.fields

  override def load: String = {
    val (newmatchField, newPlayerIndex, newPlayers) = fileIO.load
    matchField = newmatchField
    currentPlayerIndex = newPlayerIndex
    playerList = game.setPlayers(newPlayers)
    state = GameState(this)
    publish(new FieldChanged)
    "load"
  }

  override def save: String = {
    fileIO.save(matchField, currentPlayerIndex, playerList)
    publish(new FieldChanged)
    "save"
  }
}
