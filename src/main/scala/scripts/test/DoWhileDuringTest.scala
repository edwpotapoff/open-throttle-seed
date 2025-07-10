package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{DoWhileDuring, Load, LoadSteps}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


class DoWhileDuringTest extends Script {
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

  var count = 0
  val cond = () => count < 10


  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val clean = () => {
    count = 0
    empty
  }

  val countPlus = () => {
    count += 1
    empty
  }

  val load = Load(
    DoWhileDuring(cond, 100 millis)(rom, countPlus)
    , anna
    , clean
  )
}

object DoWhileDuringTest extends ScriptObj {

  def apply() = new DoWhileDuringTest
}