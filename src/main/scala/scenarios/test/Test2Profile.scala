package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{HandProfile, ScriptObj}
import scripts.test.{ScriptAnna, ScriptRom}

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.language.postfixOps


class Test2Profile extends HandProfile {
  override val
  loadScripts: Set[ScriptObj] = Set(
    ScriptRom
    , ScriptAnna
  )
  override val
  testersNum: Int = 10

  val runImmediately: Boolean = true
  val requestsNum: Int = 10
  val openThrottle = 10 millis
  val statPeriod: FiniteDuration = 10 seconds
}