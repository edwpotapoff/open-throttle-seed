package scripts.test

import io.ot._
import io.ot.builder.{Endpoint, Http, Message}
import io.ot.builder.Load


object Subaction extends ScriptObj {
  def apply() = new Subaction
}

class Subaction extends Script {
  val req = Http(
    Endpoint("http://localhost:8080/users/rom",
      Map("type" -> "GET", "status" -> 200, "timeout" -> 700)
    ),
    Message(null,
      Map("Connection" -> "keep-alive"))
  ).make()


  def step1() = req


  val load = Load(
    step1: ST,
    Map(
      "BankId" -> List(1, 2),
      "BankName" -> List("abc", "cda")
    ) -> SubSubaction
  )
}