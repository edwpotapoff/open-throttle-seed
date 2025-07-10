package scripts.test

import io.ot.builder.*
import io.ot.{Script, ScriptObj}

import scala.language.postfixOps


class Http2Test extends Script {
  val check = () =>
    asserts

  val load = Load(
    Http("https://127.0.0.1/", Map("http2" -> true)),
    check
  )
}

object Http2Test extends ScriptObj {
  def apply() = new Http2Test
}
