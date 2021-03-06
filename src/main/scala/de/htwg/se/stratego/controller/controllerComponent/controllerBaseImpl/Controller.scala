package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import com.google.inject.{Guice, Inject}
import de.htwg.se.stratego.StrategoModule
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.model.matchFieldComponent._
import de.htwg.se.stratego.util.UndoManager

import scala.collection.mutable.ListBuffer
import scala.swing.Publisher
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import de.htwg.se.stratego.controller.controllerComponent._
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Colour, Field, Figure, Game, GameCharacter, MatchField, Matrix, Player}
import play.api.libs.json.{JsNumber, JsObject, JsValue, Json}

import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.xml.{Elem, PrettyPrinter}

class Controller @Inject()(matchField:MatchFieldInterface) extends ControllerInterface with Publisher {

  implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext = system.executionContext

  val uri = "fileio_service"

  val port = 8081

  val list = CharacterList(matchField.fields.matrixSize)
  val playerBlue = Player("PlayerBlue", list.getCharacterList())
  val playerRed = Player("PlayerRed", list.getCharacterList())
  var game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)
  val playerList = List[Player](playerBlue, playerRed)
  val playerListBuffer: ListBuffer[Player] = ListBuffer.empty

  var gameStatus: GameStatus = IDLE
  var currentPlayerIndex: Int = 0
  private val undoManager = new UndoManager
  var state: ControllerState = EnterPlayer(this)

  def handle(input: String): String = {
    state.handle(input)
  }

  def setPlayers(input: String): String = {
    // allowed size of playerlistbuffer is 2, otherwise clear it
    playerListBuffer.clear()
    for (i <- setPlayer(input)) {
        playerListBuffer.append(i)
    }
    nextState
    publish(new PlayerChanged)
    ""
  }

  def setPlayer(input: String): List[Player] = {
    input.split(" ").map(_.trim).toList match{
      case player1 :: player2 :: Nil =>
        val Game = game.copy(playerA = Player(player1, game.bList), playerB = Player(player2, game.rList), size = game.size, matchField = matchField)
        val playerList = List[Player](Game.playerA, Game.playerB)
        playerList
    }
  }

  def createEmptyMatchfield(size:Int): String = {
    game = game.copy(Player("PlayerBlue", list.getCharacterList()), Player("PlayerRed", list.getCharacterList()), size, new MatchField(size, size, false))
    gameStatus=NEW
    state = EnterPlayer(this)
    publish(new NewGame)
    currentPlayerIndex=0
    "created new matchfield\nPlease enter the names like (player1 player2)"
  }

  def initMatchfield(): String = {
    var newMatchField = matchField
    newMatchField = game.init(matchField, 0, 0, 0).matchField
    if (matchField.equals(newMatchField)) {
      ""
    } else {
      game = game.copy(matchField = game.init(matchField, 0, 0, 0).matchField)
      gameStatus=INIT
      nextState
      publish(new MachtfieldInitialized)
      playerList(currentPlayerIndex) + " it's your turn!"
    }
  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): String ={
    if(game.onlyBombAndFlag(game.matchField,currentPlayerIndex,0,0) && game.matchField.fieldIsSet(rowA,colA) &&
      game.matchField.fieldColor(rowA,colA) == currentPlayerIndex) {
      currentPlayerIndex = nextPlayer
      publish(new GameFinished)
      currentPlayerIndex=1
      nextState
      createEmptyMatchfield(game.matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if(game.flagFound(rowD, colD, rowA, rowD, currentPlayerIndex)) {
      publish(new GameFinished)
      currentPlayerIndex=1
      nextState
      createEmptyMatchfield(matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if (game.attackValid(rowD, colD, rowA, colA, currentPlayerIndex)) {
      game = game.copy(matchField = game.Context.attack(game.matchField, rowA, colA, rowD, colD,currentPlayerIndex))
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
    if (game.matchField.fieldIsSet(row,col) && game.matchField.fieldColor(row,col) == currentPlayerIndex) {
      if(game.onlyBombAndFlag(game.matchField,currentPlayerIndex,0,0)) {
        currentPlayerIndex = nextPlayer
        publish(new GameFinished)
        currentPlayerIndex=1
        nextState
        createEmptyMatchfield(game.matchField.size)
        return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
          "Game finished! Play new Game with (n)!"
      }
      undoManager.doStep(new MoveCommand(dir, game.matchField, row, col, currentPlayerIndex, this))
      if (!game.matchField.fieldIsSet(row,col)) {
        currentPlayerIndex = nextPlayer
        gameStatus = MOVE
        publish(new FieldChanged)
        publish(new PlayerSwitch)
      }
    }
    ""
  }

  def matchFieldToString: String = game.matchField.toString

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

  override def getSize: Int = game.matchField.fields.matrixSize

  override def getField: Matrix[Field] = game.matchField.fields

  override def load: String = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = s"http://${uri}:${port}/fileIO"))
    responseFuture.onComplete {
      case Failure(_) => sys.error("HttpResponse failure")
      case Success(res) =>
        Unmarshal(res.entity).to[String].onComplete {
          case Failure(_) => sys.error("Marshal failure")
          case Success(result) =>
            print(result)
            if(result.startsWith("{")) {
              println("unpack json")
              unpackJson(result)
            }
            if(result.startsWith("<")) {
              println("unpack xml")
              unpackXML(result)
            }
        }
    }
    "load"
  }

  def unpackXML(result: String): Unit = {
    val file = scala.xml.XML.loadString(result)
    val newPlayerIndex = (file \\ "matchField" \ "@currentPlayerIndex").text.toInt
    val playerS = (file \\ "matchField" \ "@players").text
    val injector = Guice.createInjector(new StrategoModule)
    var newMatchField = injector.getInstance(classOf[MatchFieldInterface])
    val fieldNodes = (file \\ "field")
    for(field <- fieldNodes){
      val row: Int = (field \ "@row").text.toInt
      val col: Int = (field\ "@col").text.toInt
      val figName: String = (field\ "@figName").text
      val figValue: Int = (field\ "@figValue").text.toInt
      val colour:Int = (field\ "@colour").text.toInt
      newMatchField = matchField.addChar(row, col, GameCharacter(Figure.FigureVal(figName,figValue)),
        Colour.FigureCol(colour))
    }
    game = game.copy(matchField = newMatchField)
    currentPlayerIndex = newPlayerIndex
    setPlayers(playerS)
    state = GameState(this)
    gameStatus=LOAD
    publish(new FieldChanged)
    publish(new LoadGame)
  }

  def unpackJson(result: String): Unit = {
    val json: JsValue = Json.parse(result)
    val injector = Guice.createInjector(new StrategoModule)
    var newMatchField = injector.getInstance(classOf[MatchFieldInterface])
    val newPlayerIndex = (json \ "currentPlayerIndex").get.toString().toInt
    val playerS = (json \ "players").get.toString().replaceAll("\"", "").replaceAll("\\\\", "")
    for(index <- 0 until matchField.fields.matrixSize * matchField.fields.matrixSize){
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      if(((json \ "matchField")(index) \\ "figName").nonEmpty) {
        val figName = ((json \ "matchField")(index) \ "figName").as[String]
        val figValue = ((json \ "matchField")(index) \ "figValue").as[Int]
        val colour = ((json \ "matchField")(index) \ "colour").as[Int]
        newMatchField = newMatchField.addChar(row, col, GameCharacter(Figure.FigureVal(figName, figValue)), Colour.FigureCol(colour))
      }
    }
    game = game.copy(matchField = newMatchField)
    currentPlayerIndex = newPlayerIndex
    setPlayers(playerS)
    state = GameState(this)
    gameStatus=LOAD
    publish(new FieldChanged)
    publish(new LoadGame)
  }

  def matchFieldToJson(matchField: MatchFieldInterface, currentPlayerIndex: Int, players: String): JsObject = {
    Json.obj(
      "currentPlayerIndex" -> JsNumber(currentPlayerIndex),
      "players" -> players,
      "matchField"-> Json.toJson(
        for{
          row <- 0 until matchField.fields.matrixSize
          col <- 0 until matchField.fields.matrixSize
        } yield {
          var obj = Json.obj(
            "row" -> row,
            "col" -> col
          )
          if(matchField.fields.field(row,col).isSet) {
            obj = obj.++(Json.obj(
              "figName" -> matchField.fields.field(row, col).character.get.figure.name,
              "figValue" -> matchField.fields.field(row, col).character.get.figure.value,
              "colour" -> matchField.fields.field(row, col).colour.get.value
            )
            )
          }
          obj
        }
      )
    )
  }

  def cellToXml(matchField: MatchFieldInterface, row: Int, col: Int): Any = {
    if(matchField.fields.field(row,col).isSet){
      <field row={row.toString} col={col.toString} figName={matchField.fields.field(row,col).character.get.figure.name}
             figValue={matchField.fields.field(row,col).character.get.figure.value.toString}
             colour={matchField.fields.field(row,col).colour.get.value.toString}>
      </field>
    }
  }

  def matchFieldToXml(matchField: MatchFieldInterface, currentPlayerIndex: Int, playerS: String): Elem ={
    <matchField  currentPlayerIndex={ currentPlayerIndex.toString} players={playerS}>
      {
      for{
        row <- 0 until matchField.fields.matrixSize
        col <- 0 until matchField.fields.matrixSize
      } yield cellToXml(matchField, row, col)
      }
    </matchField>

  }

  override def save: Unit = {
    val players = if (playerListBuffer.isEmpty) playerList else playerListBuffer.toList
    publish(new FieldChanged)
    gameStatus=SAVE
    val playerS = players(0) + " " + players(1)
    val prettyPrinter = new PrettyPrinter(120,4)
    val gamestate = Json.prettyPrint(matchFieldToJson(game.matchField, currentPlayerIndex, playerS))
    print(gamestate)
    val gamestatexml = prettyPrinter.format(matchFieldToXml(game.matchField, currentPlayerIndex, playerS))
    val responseFuture: Future[HttpResponse] = Http()
      .singleRequest(HttpRequest(method = HttpMethods.POST, uri = s"http://${uri}:${port}/fileIO/json", entity = gamestate))
    val responseFuturexml: Future[HttpResponse] = Http()
      .singleRequest(HttpRequest(method = HttpMethods.POST, uri = s"http://${uri}:${port}/fileIO/xml", entity = gamestatexml))
  }

  override def savedb: Unit = {
    val players = if (playerListBuffer.isEmpty) playerList else playerListBuffer.toList
    publish(new FieldChanged)
    gameStatus=SAVE
    val playerS = players(0) + " " + players(1)
    val gamestate: String = Json.prettyPrint(matchFieldToJson(game.matchField, currentPlayerIndex, playerS))
    val dbfuture: Future[HttpResponse] = Http()
      .singleRequest(HttpRequest(method = HttpMethods.POST, uri = s"http://${uri}:${port}/savedb", entity = gamestate))
  }

  override def loaddb: Unit = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = s"http://${uri}:${port}/loaddb"))
    responseFuture.onComplete {
      case Failure(_) => sys.error("HttpResponse failure")
      case Success(res) => {
        Unmarshal(res.entity).to[String].onComplete {
          case Failure(_) => sys.error("Marshal failure")
          case Success(result) => {
            unpackJson(result)
          }
        }
      }
    }
  }

  override def deletedb: Unit = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = s"http://${uri}:${port}/deletedb"))
  }
}
