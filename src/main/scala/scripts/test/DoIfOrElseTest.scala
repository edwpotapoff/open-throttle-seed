package scripts.test

import io.ot.*
import io.ot.builder.{DoIfOrElse, Http, Load, LoadSteps}

import scala.language.postfixOps


class DoIfOrElseTest extends Script {
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

  val cond = () => true


  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val load = Load(
    DoIfOrElse(cond)(rom)(anna)
  )
}

object DoIfOrElseTest extends ScriptObj {
  def apply() = new DoIfOrElseTest
}