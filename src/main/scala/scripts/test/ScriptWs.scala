package scripts.test

import io.ot.builder.{Load, WsBinaryMessage, WsClose, WsOpen, WsTextMessage}
import io.ot.{Script, ScriptObj}
import scala.language.postfixOps


object ScriptWs extends ScriptObj {
  def apply() = new ScriptWs
}

class ScriptWs extends Script {
  val load = Load(
    WsOpen("ws://localhost:8080/greeter", timeout = 1000), // ms
    WsTextMessage("User${testerNum}", 500),
    () => {
      log.info(tc.response.string)
      tc("ar") = s"UseR$testerNum".getBytes
    },
    WsBinaryMessage("${ar}", 500),
    () => {
      _assert(tc.response.string == "Hello UseR1!", s"response != UseR$testerNum", s"response == UseR$testerNum")
      asserts
    },
    WsClose
  )
}
