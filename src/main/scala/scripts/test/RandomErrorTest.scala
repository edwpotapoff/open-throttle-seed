package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{Load, LoadSteps}

import scala.language.postfixOps


class RandomErrorTest extends Script {
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

  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val load = Load(
    rom
    , () => {
      if (state == RUN && rand(2) % 2 == 0)
        throw new Exception("test")
      empty
    }
    , anna
  )
}

object RandomErrorTest extends ScriptObj {
  def apply() = new RandomErrorTest
}