package scenarios.test

import io.ot.DebugScenario
import io.ot.api.annotation.ShowScenario
import scripts.test.CSVFeederLocal
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

@ShowScenario
class CSVFeederLocalDebug extends DebugScenario {
  val script = CSVFeederLocal
  val testersNum = 10
  val requestsNum = 2
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
  override val kShareData: Int = -1 // -1 один источник на узел
}