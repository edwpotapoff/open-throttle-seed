package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{Forever, Load, LoadSteps}

import scala.language.postfixOps


class ForeverTest extends Script {
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


  val do1 = // последовательность действий в отдельном объекте
    Seq(
      () => requests(0)
      , check(users(0))
    )

  val load = Load(
    Forever(do1, "count")
    , () => requests(1)
    , check(users(1))
  )
}

object ForeverTest extends ScriptObj {
  def apply() = new ForeverTest
}