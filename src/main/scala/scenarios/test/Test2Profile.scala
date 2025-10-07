package scenarios.test

import io.ot.{HandProfile, Max, ScenarioObj, ScriptObj}
import scripts.test.{ScriptAnna, ScriptRom}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


class Test2Profile extends HandProfile:
  override val loadProfile = Map(
    ScriptRom -> (1.0, 1)
  )

  val runImmediately = true
  val requestsNum = 10
  val openThrottle = 10 millis
  val statPeriod = 10 seconds

object Test2Profile extends ScenarioObj:
  def apply() = new Test2Profile

class MaxTest2Profile extends Max:
  val scenario = Test2Profile
  val stepsNumber = 5
  val stepDuration = 30 seconds
  val startK = 10
  val stepK = 1 // при минимальном количестве базовых пользователей stepK должно быть минимум в количество нод (либо базовое количество пользователей)

