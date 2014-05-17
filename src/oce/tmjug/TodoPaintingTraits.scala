/**
 *
 */
package oce.tmjug

import java.awt.Color
import java.awt.Graphics2D
import scala.swing.BorderPanel
import scala.swing.BoxPanel
import scala.swing.Button
import scala.swing.FlowPanel
import scala.swing.Label
import scala.swing.MainFrame
import scala.swing.Panel
import scala.swing.SimpleSwingApplication
import scala.swing.SimpleSwingApplication
import scala.swing.TextField
import scala.swing.event.ButtonClicked
import scala.swing.Orientation
import java.awt.BasicStroke
/**
 * @author Sile
 *
 */

trait BoldText { this: Label =>
  font = BoldText.this.font.deriveFont(15.f)
}

trait RedBackground { this: Label => {
    opaque = true
    background = Color.red
  }
}

trait WhiteStripes extends Label {
  override protected def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    g.setColor(Color.white)
    g.setStroke(new BasicStroke(3))
    for (x <- 0 until size.width by 15) g.drawLine(x, 0, x, size.height)
  }
}
object TodoPaintingTraits extends SimpleSwingApplication {
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
      contents ++= tasks.map(n => new Label(n) with BoldText with WhiteStripes with RedBackground)

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
          taskList.contents += new Label(textField.text) with BoldText with WhiteStripes with RedBackground
          taskList.peer.doLayout()
        }
      }
    }
  }
}