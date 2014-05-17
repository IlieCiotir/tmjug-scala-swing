/**
 * https://code.google.com/p/scala-samples/source/browse/trunk/simplecalculator/src/main/scala/MigPanel.scala
 */
package oce.tmjug
import swing._
import _root_.net.miginfocom.swing._
import scala.collection.mutable._
/**
 * MigPanel
 *
 * Custom Scala wrapper for MigLayout
 *
 * @author raam
 * @version 0.1
 */
class MigPanel(constraints: String, colConst: String, rowConst: String) extends Panel with LayoutContainer {

  def this(constraints: String) = this(constraints, "", "")

  type Constraints = String

  def layoutManager = peer.getLayout.asInstanceOf[MigLayout]

  override lazy val peer = new javax.swing.JPanel(new MigLayout(constraints, colConst, rowConst)) with SuperMixin

  override def contents: MigContent = new MigContent

  protected class MigContent extends Content { def +=(c: Component, l: Constraints) = add(c, l) }

  protected def constraintsFor(comp: Component) =
    layoutManager.getConstraintMap.get(comp.peer).toString

  protected def areValid(c: Constraints): (Boolean, String) = (true, "")

  protected def add(c: Component, l: Constraints) = peer.add(c.peer, l)

}