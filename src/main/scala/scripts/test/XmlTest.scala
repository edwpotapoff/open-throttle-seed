package scripts.test

import io.ot.builder.*
import io.ot.{Script, ScriptObj}
import scala.language.postfixOps

class XmlTest extends Script {
  val load = Load(
    Http("/", "lh")
    , Xpath("/html/body", "==", "Hello world!")
  )
}

object XmlTest extends ScriptObj {
  def apply() = new XmlTest
}
