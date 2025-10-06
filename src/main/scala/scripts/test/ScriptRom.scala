package scripts.test

import io.ot.builder.{Http, Load}
import io.ot.{Script, ScriptObj, builder}

object ScriptRom extends ScriptObj:
  def apply() = new ScriptRom

class ScriptRom extends Script:
  val load = Load(Http("/users/rom", "lh"))

