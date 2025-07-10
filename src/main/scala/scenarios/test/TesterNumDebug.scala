package scenarios.test

import io.ot.DebugScenario
import io.ot.api.annotation.ShowScenario
import scripts.test.TesterNum
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

@ShowScenario
class TesterNumDebug extends DebugScenario {
  val script = TesterNum
  val testersNum = 2
  val requestsNum = 1
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}