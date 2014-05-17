/**
 *
 */
package oce.tmjug

import scala.swing.MainFrame
import scala.swing.SimpleSwingApplication
import scala.swing.TextField
import scala.swing.BorderPanel
import scala.swing.BorderPanel.Position._
import scala.swing.BoxPanel
import scala.swing.Orientation
import scala.swing.Button
import java.awt.Dimension
import scala.swing.Label
import scala.swing.Reactions
import scala.swing.event.ButtonClicked
import scala.swing.event.MouseMoved
/**
 * @author Sile
 *
 */
object Todo extends SimpleSwingApplication {
  override def startup(args: Array[String]) {
    System.out.println("I'm alive!")
    super.startup(args)
  }

  override def shutdown() {
    System.out.println("I'm done!")
  }

  def top = new MainFrame {
    title = "Todo todo todo todo todooo todo do do"

    val textField = new TextField
    val tasks = List(
      "I'm gonna fight 'em all",
      "A seven nation army couldn't hold me back",
      "They're gonna rip it off",
      "Taking their time right behind my back",
      "And I'm talking to myself at night",
      "Because I can't forget",
      "Back and forth through my mind",
      "Behind a cigarette",
      "And the message coming from my eyes")
    val taskList = new BoxPanel(Orientation.Vertical) {
      contents ++= tasks.map(n => new Label(n))
    }
    taskList.contents += new Label("aa")
    val addButton = new Button("Add")
    contents = new BorderPanel {
      layout(textField) = BorderPanel.Position.North
      layout(taskList) = BorderPanel.Position.Center
      layout(addButton) = BorderPanel.Position.South

    }

    listenTo(addButton, taskList.mouse.moves)
    reactions += {
      case ButtonClicked(button) => button match {
        case `addButton` => {
          taskList.contents += new Label(textField.text)
          taskList.peer.doLayout()
        }
      }
      case e: MouseMoved => System.out.println(e.toString)
    }
  }
}