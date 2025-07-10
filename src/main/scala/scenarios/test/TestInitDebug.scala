package scenarios.test

import io.ot.DebugScenario
import scripts.test.TestInitScript

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


class TestInitDebug extends DebugScenario {
  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
  val script = TestInitScript
  val testersNum = 1
  val requestsNum = 1
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}
