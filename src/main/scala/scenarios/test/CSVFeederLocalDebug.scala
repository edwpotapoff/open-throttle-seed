package scenarios.test

import io.ot.DebugScenario
import scripts.test.CSVFeederLocal
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

class CSVFeederLocalDebug extends DebugScenario:
  val script = CSVFeederLocal
  val testersNum = 1
  val requestsNum = 2
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
