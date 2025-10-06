package scripts.test

import io.ot.builder.{Http, Load}
import io.ot.{Script, ScriptObj, builder}

object ScriptAnna extends ScriptObj:
  def apply() = new ScriptAnna


class ScriptAnna extends Script:
  val load = Load(Http("/users/anna", "lh"))
