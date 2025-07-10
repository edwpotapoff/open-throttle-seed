package scripts.test

import io.ot.{Script, ScriptObj, builder}

object TesterNum extends ScriptObj {
  def apply() = new TesterNum
}

class TesterNum extends Script {
  def check0() = {
    log.info(s"testerNum $testerNum")
    empty
  }

  val load = builder.LoadSteps(check0)
}