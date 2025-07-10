package scripts.test

import io.ot.*
import io.ot.builder.{Http, Load, LoadSteps}

import scala.language.postfixOps


class InitScript extends Script {

  val load = Load(
    Http("/users/anna", "lh")
    , () => tc("user") = "rom"
  )
}

object InitScript extends ScriptObj {
  def apply() = new InitScript
}