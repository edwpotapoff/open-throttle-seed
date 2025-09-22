package scripts.test

import io.ot.builder.{Load, WsMessage, WsClose, WsOpen}
import io.ot.{Script, ScriptObj}
import scala.language.postfixOps


object ScriptWs extends ScriptObj {
  def apply() = new ScriptWs
}

class ScriptWs extends Script {
  val load = Load(
    WsOpen("ws://localhost:8080/greeter", timeout = 1000), // ms
    WsMessage("User${testerId}", 500),
    () => {
      log.info(tc.response.string)
      tc("ar") = s"UseR$testerId".getBytes
    },
    WsMessage("${ar}", 500),
    () => {
      _assert(tc.response.string == "Hello UseR1!", s"response != UseR$testerId", s"response == UseR$testerId")
      asserts
    },
    WsClose
  )
}
