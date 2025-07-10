package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{During, Load, LoadSteps}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class DuringTest extends Script {
  val users = Array("rom", "anna")
  var requests: Array[CO] = _

  def fillRequests() = {
    requests = users.map {
      user =>
        Http(s"lh:/users/$user")
          .make()
    }

    empty
  }

  override val init = LoadSteps(fillRequests)

  def check(name: String) = () => {
    subStr(name)
    asserts
  }

  val load = Load(
    During(100 millis, "count")(
      () => requests(0)
      , check(users(0))
    )
    , () => requests(1)
    , check(users(1))
  )
}

object DuringTest extends ScriptObj {
  def apply() = new DuringTest
}