package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{Load, LoadSteps, TryMax}

import scala.language.postfixOps


class TryMaxTest extends Script {
  val users = Array("rom", "anna")
  var requests: Array[CO] = _

  def fillRequests() = {
    requests = users.map {
      user =>
        Http(s"lh:/users/$user")
          .make()
    }
    tc("n") = 5
    empty
  }

  override val init = LoadSteps(fillRequests)

  def check(name: String) = () => {
    if (name == "rom") {
      val c = tc("counter").int
      if (c < 2)
        throw new Exception("test")
    }
    subStr(name)
    asserts
  }

  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val load = Load(
    TryMax("n", "counter")(rom)
    , anna
  )
}

object TryMaxTest extends ScriptObj {
  def apply() = new TryMaxTest
}