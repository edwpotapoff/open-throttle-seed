package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{ExitBlockOnFail, Load, LoadSteps}

import scala.language.postfixOps


class ExitBlockOnFailTest extends Script {
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
    subStr(name)
    asserts
  }

  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val load = Load(
    ExitBlockOnFail(
      () => {
        assert(false)
        empty
      }
      , rom
    )
    , anna
  )
}

object ExitBlockOnFailTest extends ScriptObj {
  def apply() = new ExitBlockOnFailTest
}