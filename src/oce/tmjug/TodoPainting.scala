/**
 *
 */
package oce.tmjug

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D

import scala.swing.BorderPanel
import scala.swing.Button
import scala.swing.FlowPanel
import scala.swing.Label
import scala.swing.MainFrame
import scala.swing.SimpleSwingApplication
import scala.swing.TextField
import scala.swing.event.ButtonClicked

/**
 * @author Sile
 *
 */
object TodoPainting extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Todo todo todo todo todooo todo do do"

    val textField = new TextField
    val tasks = List("a", "b", "c")
    val taskList = new FlowPanel {
      contents ++= tasks.map(n => new Label(n) {
        background = Color.RED
        opaque = true
        override protected def paintComponent(g: Graphics2D) {
          super.paintComponent(g)
          g.setColor(Color.WHITE)
          g.setStroke(new BasicStroke(3))
          for (x <- 0 until size.width by 15) g.drawLine(x, 0, x, size.height)
        }
      })
    }

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
    }
  }
}