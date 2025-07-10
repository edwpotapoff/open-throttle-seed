package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.Load

import scala.language.postfixOps


class FormParamTest extends Script {

  val page = Http(s"lhp:/new", POST, body = "flightID=210297424-100296-JB&2=on")
    .make()

  val load =
    Load(
      () => page
    )
}

object FormParamTest extends ScriptObj {
  def apply() = new FormParamTest
}