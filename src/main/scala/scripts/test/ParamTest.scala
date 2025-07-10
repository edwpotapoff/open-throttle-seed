package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{CloseStep, Load}

import scala.language.postfixOps


class ParamTest extends Script {

  val page = Http(s"lh:/page?p=1&end=3")
    .make()

  val load =
    Load(
      () => page
    )
}

object ParamTest extends ScriptObj {
  def apply() = new ParamTest
}