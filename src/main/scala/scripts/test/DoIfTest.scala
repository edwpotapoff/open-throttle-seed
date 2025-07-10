package scripts.test

import io.ot.*
import io.ot.builder.{DoIf, Http, Load, LoadSteps, Repeat}

import scala.language.postfixOps


class DoIfTest extends Script {
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

  var firstTime = true

  val cond =
    () =>
      val ret = firstTime
      firstTime = false
      ret


  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val load = Load(
    DoIf(cond)(rom)
    , anna
  )
}

object DoIfTest extends ScriptObj {
  def apply() = new DoIfTest
}