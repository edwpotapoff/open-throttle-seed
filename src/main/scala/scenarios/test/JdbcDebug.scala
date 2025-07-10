package scenarios.test

import io.ot.DebugScenario
import io.ot.api.annotation.ShowScenario
import scripts.test.ScriptJdbc
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

@ShowScenario
class JdbcDebug extends DebugScenario {
  val script = ScriptJdbc
  val testersNum = 101
  val requestsNum = 100
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  override val statTemplate = true
  val statPeriod = 10 seconds
}