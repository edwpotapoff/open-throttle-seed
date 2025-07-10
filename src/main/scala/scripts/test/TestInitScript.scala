package scripts.test

import io.ot.*
import io.ot.builder.{Http, Load, LoadSteps}

import scala.language.postfixOps


class TestInitScript extends Script {

  override val init = Load(
    () =>
      log.info("начало инициализации")
    , InitScript
  )

  val load = Load(
    () =>
      log.info(s"tc = ${tc.attributes}")
    , Http("/users/${user}", "lh")
  )
}

object TestInitScript extends ScriptObj {
  def apply() = new TestInitScript
}