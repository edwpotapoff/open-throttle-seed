package scenarios.test

import io.ot.DebugScenario
import io.ot.api.annotation.ShowScenario
import scripts.test.CSVFeeder
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

@ShowScenario
class CSVFeederDebug extends DebugScenario {
  val script = CSVFeeder
  val testersNum = 4
  val requestsNum = 10
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}