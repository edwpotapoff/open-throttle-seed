package scenarios.test

import io.ot.DebugScenario
import scripts.test.ScriptJdbc
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

class JdbcDebug extends DebugScenario:
  val script = ScriptJdbc
  val testersNum = 1
  val requestsNum = 2
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
