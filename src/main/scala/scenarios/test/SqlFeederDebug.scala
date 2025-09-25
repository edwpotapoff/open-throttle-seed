package scenarios.test

import io.ot.DebugScenario
import scripts.test.SqlFeeder
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

class SqlFeederDebug extends DebugScenario:
  val script = SqlFeeder
  val testersNum = 100
  val requestsNum = 10
  val openThrottle = 100 millis
  val runImmediately = true // after opening
  override val statName = true
  val statPeriod = 10 seconds
