package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{CloseStep, Load}

import scala.language.postfixOps


class CloseTest extends Script {

  val rom = Http(s"lh:/users/rom")
    .make()

  val load =
    Load(
      () => rom
      , CloseStep // () => close()
    )
}

object CloseTest extends ScriptObj {
  def apply() = new CloseTest
}