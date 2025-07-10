package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{Load, LoadSteps}

import scala.language.postfixOps


class StopTestTest extends Script {
  val users = Array("rom", "anna")
  var requests: Array[CO] = _

  def fillRequests() = {
    requests = users.map {
      user =>
        Http(s"lh:/users/$user")
          .make()
    }
    tc("condition") = false
    empty
  }

  override val init = LoadSteps(fillRequests)

  def check(name: String) = () => {
    subStr(name)
    asserts
  }

  val rom = Load(() => requests(0), check(users(0)))
  val anna = Load(() => requests(1), check(users(1)))

  val load =
    Load(
      rom
      , () => {
        if (state == RUN && testerNum == 1)
        //          stopTest("Тестовая остановка теста") // crash = false
          stopTest("Критическая ошибка", true) // crash = false

        else
          empty
      }

      , anna
    )
}

object StopTestTest extends ScriptObj {
  def apply() = new StopTestTest
}