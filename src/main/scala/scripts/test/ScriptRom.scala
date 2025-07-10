package scripts.test

import io.ot.builder.Http
import io.ot.{Script, ScriptObj, builder}

object ScriptRom extends ScriptObj {
  def apply() = new ScriptRom
}

class ScriptRom extends Script {
  val req = Http("/users/rom", "lh")
    .make()


  def step1() = req

  val load = builder.LoadSteps(step1)
}
