package scripts.test

import io.ot.builder.Load
import io.ot.{Script, ScriptObj}

object EmptyScript extends ScriptObj {
  def apply() = new EmptyScript
}

class EmptyScript extends Script {
  val load = Load(() => ())
}
