/**
 *
 */
package oce.tmjug

import scala.swing.SimpleSwingApplication
import scala.swing.event.ButtonClicked
import scala.swing.MainFrame
import scala.swing.BoxPanel
import scala.swing.Button
import scala.swing.TextField
import scala.swing.Orientation
import scala.swing.BorderPanel
import scala.swing.Label
import scala.swing.event.MouseMoved
import scala.swing.ListView
import scala.swing.event.SelectionChanged
import scala.swing.event.ButtonClicked
import scala.swing.event.ButtonClicked

/**
 * @author Sile
 *
 */
object TodoListViews extends SimpleSwingApplication {
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
    val taskList = new ListView(tasks) {
      renderer = ListView.Renderer(_.toUpperCase())
    }
    val addButton = new Button("Add")
    val deleteButton = new Button("Delete")
    contents = new BorderPanel {
      layout(textField) = BorderPanel.Position.North
      layout(taskList) = BorderPanel.Position.Center
      layout(addButton) = BorderPanel.Position.South
      layout(deleteButton) = BorderPanel.Position.East
    }

    listenTo(addButton, taskList, deleteButton)
    reactions += {
      case ButtonClicked(button) => button match {
        case `addButton` =>
          taskList.listData = taskList.listData :+ textField.text
        case `deleteButton` => {
          taskList.listData = taskList.listData.filter(t => !taskList.selection.items.contains(t))
        }
      }
    }
  }
}