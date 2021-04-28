package de.htwg.se.stratego.aview.gui

import java.awt.image.BufferedImage
import java.awt.{Color, Font, Point, Toolkit}
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, LoadGame, PlayerChanged}

import javax.imageio.ImageIO
import javax.swing.border.LineBorder
import javax.swing.{BorderFactory, ImageIcon, JFrame, WindowConstants}
import scala.swing.event.ButtonClicked
import scala.swing.{Button, Dimension, FlowPanel, Frame, GridPanel, Label, TextField}

class PlayerFrame(controller:ControllerInterface) extends Frame{

  listenTo(controller)

  val strategoImg: BufferedImage = ImageIO.read(getClass.getResource("stratego.png"))
  val strategoI = new ImageIcon(strategoImg)
  val defaultColor = new Color(143,138,126)
  val lightG = new Color(192,192,192)
  val lightF = new Font("Calibri", 1, 25)
  val defaultFont = new Font("Calibri", Font.BOLD, 30)
  val defaultBorder = new LineBorder(java.awt.Color.WHITE,10)
  val iconImg: BufferedImage = ImageIO.read(getClass.getResource("iconS.png"))

  title = "Stratego"
  iconImage = iconImg
  peer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  resizable= false
  //peer.setLocationRelativeTo(null)
  visible= true

  val player1: TextField = new TextField("", 20){
    foreground= lightG
    font = lightF
    border = BorderFactory.createEmptyBorder(0,20,0,0)
  }

  val player2: TextField = new TextField("", 20){
    foreground= lightG
    font = lightF
    border = BorderFactory.createEmptyBorder(0,20,0,0)
  }

  def img: Label = new Label{
    icon = strategoI
  }

  def welcomeString: Label = new Label{
    text = "Welcome to"
    foreground= defaultColor
    font = defaultFont
  }

  def welcomePanel: FlowPanel = new FlowPanel() {
    contents += welcomeString
    contents += img
  }

  def setPanel(): GridPanel = new GridPanel(2,2){
    contents += new Label {
      text = "Player 1:"
      foreground= defaultColor
      font = defaultFont
    }
    contents += player1
    vGap = 10
    contents += new Label {
      text = "Player 2:"
      foreground= defaultColor
      font = defaultFont
    }
    contents += player2
  }


  val next: Button = new Button{
    text = "Play"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
  }

  val load: Button = new Button{
    text = "Load Game"
    font = defaultFont
    background = lightG
    foreground = Color.WHITE
  }

  val quit: Button = new Button{
    text = "Quit"
    font = defaultFont
    background = lightG
    foreground = Color.WHITE
  }

  def emptyPanel = new FlowPanel

  def buttonPanel: GridPanel = new GridPanel(3,2) {
    border = BorderFactory.createEmptyBorder(40,0,0,0)
    vGap = 30
    contents += emptyPanel
    contents += next
    contents += emptyPanel
    contents += load
    contents += emptyPanel
    contents += quit
  }

  def loadGame(): Unit = {
    visible = false
    deafTo(controller)
    close()
    Thread.sleep(2000)
    new GameFrame(controller)
  }

  listenTo(load)
  reactions += {
    case ButtonClicked(`load`) =>
      controller.load
      loadGame()
  }

  reactions += {
    case event: LoadGame =>
      loadGame()
  }

  listenTo(next)

  reactions += {
    case event: PlayerChanged     =>
      visible = false
      deafTo(controller)
      close()
      new SetFrame(controller)
  }

  listenTo(quit)
  reactions += {
    case ButtonClicked(`quit`) =>
      System.exit(0)
  }

  reactions += {
    case ButtonClicked(`next`) =>
      listenTo(controller)
      if(player1.text.isEmpty || player2.text.isEmpty) {
        controller.handle("Player1"+ " "+ "Player2")
      }
      controller.handle(player1.text+ " "+ player2.text)
  }

  val mainPanel: GridPanel = new GridPanel(3,1) {
    contents += img
    vGap = 30
    contents += setPanel
    contents += buttonPanel
    border = BorderFactory.createEmptyBorder(0,0,80,90)
  }

  contents = mainPanel

}
