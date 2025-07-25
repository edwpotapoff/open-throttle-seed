package scripts.test

import io.ot.builder.{Endpoint, Http, Message}
import io.ot.{Script, ScriptObj, builder}


object SubSubaction extends ScriptObj {
  def apply() = new SubSubaction
}

class SubSubaction extends Script {
  val req = Http(
    Endpoint("http://localhost:8080/users/anna",
      Map("type" -> "GET", "status" -> 200, "timeout" -> 700)
    ),
    Message(null,
      Map("Connection" -> "keep-alive"))
  ).make()


  def step1() = req


  def chek() = {
    val bankId = "BankId".any
    val bankName = "BankName".string
    log.info(s"Bank: $bankId, $bankName")
    //assert(false, "test")
    empty
  }

  val load = builder.LoadSteps(step1, chek)
}