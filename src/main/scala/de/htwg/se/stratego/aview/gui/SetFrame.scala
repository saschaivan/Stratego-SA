package de.htwg.se.stratego.aview.gui

import java.awt.{Color, Font}

import scala.swing._
import scala.swing.event._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameStatus, MachtfieldInitialized, NewGame, PlayerSwitch}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import javax.imageio.ImageIO
import javax.swing.{BorderFactory, WindowConstants}
import javax.swing.border.LineBorder

class SetFrame(controller:ControllerInterface) extends Frame {

  listenTo(controller)

  val matchFieldSize = controller.getSize
  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)
  var gameStatus: GameStatus = IDLE
  def statusString:String = GameStatus.getMessage(gameStatus)
  val iconImg = ImageIO.read(getClass.getResource("iconS.png"))
  val defaultFont = new Font("Calibri", Font.BOLD, 30)
  val legendFont = new Font("Calibri", Font.BOLD, 15)
  val defaultColor = new Color(143,138,126)
  val defaultBorder = new LineBorder(java.awt.Color.WHITE,1)

  title = "Stratego"
  iconImage = iconImg
  peer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  resizable= false
  //peer.setLocationRelativeTo(null)
  visible=true

  def matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
    for{
      row <- 0 until matchFieldSize
      col <- 0 until matchFieldSize
    }{
      val fieldPanel = new FieldPanel(row, col, controller)
      fields(row)(col) = fieldPanel
      contents += fieldPanel
      listenTo(fieldPanel)
    }
  }

  val initializeButton = new Button{
    text = "set characters automatically"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
  }

  listenTo(initializeButton)
  reactions += {
    case ButtonClicked(`initializeButton`) =>
      controller.handle("i")
  }

  val status = new TextField(controller.statusString, 20)

  def statusPanel = new BorderPanel {
    add(status, BorderPanel.Position.Center)
    border = BorderFactory.createEmptyBorder(15,0,0,0)
  }

  val buttonPanel = new BorderPanel {
    add(initializeButton, BorderPanel.Position.Center)
    border = BorderFactory.createEmptyBorder(300,0,10,0)
  }

  val message = new Label{
    text= "<html>"+ "Welcome to STRATEGO!<br><br> " + controller.playerList(0).toString + " it's your turn. " +
      "Set your figures on the blue fields with following keystrokes:<br><br>" +
      "Bomb        (\uD83D\uDCA3) with B<br>" +
      "Marshal     (\uD83D\uDC82) with M<br>" +
      "General     (9) with 9<br>" +
      "Colonel     (8) with 8<br>" +
      "Major       (7) with 7<br>" +
      "Captain     (6) with 6<br>" +
      "Lieutenant  (5) with 5<br>" +
      "Sergeant    (4) with 4<br>" +
      "Miner       (3) with 3<br>" +
      "Scout       (2) with 2<br>" +
      "Spy         (1) with 1<br>" +
      "Flag        (\uD83C\uDFF3) with F<br><br>"+
      "The figures can also be set automatically by pressing the button below \uD83D\uDC47</html>"
    foreground= defaultColor
    font = legendFont
  }

  def messagePanel = new BorderPanel{
    add(message, BorderPanel.Position.Center)
  }

  def legendPanel = new GridPanel(2,1){
    contents += messagePanel
    contents += buttonPanel
    border = BorderFactory.createEmptyBorder(0,15,0,0)
    preferredSize = new Dimension(400, 100)
  }

  val mainPanel = new BorderPanel{
    add(matchfieldPanel, BorderPanel.Position.Center)
    add(legendPanel, BorderPanel.Position.East)
    add(statusPanel, BorderPanel.Position.South)
    border = BorderFactory.createEmptyBorder(20,20,20,20)
  }

  mainPanel.requestFocus()
  contents = mainPanel

  visible = true
  redraw

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game") {
        controller.createEmptyMatchfield(matchFieldSize)
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

  def redraw: Unit = {
    for {
      row <- 0 until matchFieldSize
      column <- 0 until matchFieldSize
    } fields(row)(column).redraw
    status.text = controller.statusString
    repaint
  }

  reactions += {
    case event: FieldChanged     => redraw
    case event: MachtfieldInitialized =>
      visible = false
      deafTo(controller)
      close()
      new GameFrame(controller)
    case event: NewGame =>
      deafTo(controller)
      close()
      new PlayerFrame(controller)
    case event: PlayerSwitch =>
      /*
      legendPanel.contents.clear()
      legendPanel.contents += messagePanel
      legendPanel.border = BorderFactory.createEmptyBorder(0,15,0,0)
      legendPanel.preferredSize = new Dimension(400, 100)
      *
       */

      message.text= "<html>"+ controller.playerList(1).toString + " now it's your turn. " +
        "Set your figures on the red fields with following keystrokes:<br><br>" +
        "Bomb        (\uD83D\uDCA3) with B<br>" +
        "Marshal     (\uD83D\uDC82) with M<br>" +
        "General     (9) with 9<br>" +
        "Colonel     (8) with 8<br>" +
        "Major       (7) with 7<br>" +
        "Captain     (6) with 6<br>" +
        "Lieutenant  (5) with 5<br>" +
        "Sergeant    (4) with 4<br>" +
        "Miner       (3) with 3<br>" +
        "Scout       (2) with 2<br>" +
        "Spy         (1) with 1<br>" +
        "Flag        (\uD83C\uDFF3) with F"+
        "</html>"

  }
}
