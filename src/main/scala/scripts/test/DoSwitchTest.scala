package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{DoSwitch, Load, LoadSteps}

import scala.language.postfixOps


class DoSwitchTest extends Script {
  val users = Array("rom", "anna")
  var requests: Array[CO] = _

  def fillRequests() = {
    requests = users.map {
      user =>
        Http(s"lh:/users/$user")
          .make()
    }
    tc("cond") = "rom"
    empty
  }

  override val init = LoadSteps(fillRequests)

  def check(name: String) = () => {
    subStr(name)
    asserts
  }

  val cond = () => "anna"

  val load = Load(
    DoSwitch("cond")(
      "rom" -> Load(() => requests(0), check(users(0))),
      "anna" -> Load(() => requests(1), check(users(1)))
    )
  )
}

object DoSwitchTest extends ScriptObj {
  def apply() = new DoSwitchTest
}