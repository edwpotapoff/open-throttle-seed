package scenarios.test


import io.ot.api.annotation.ShowScenario
import io.ot.{DebugScenario, ScenarioObj}
import scripts.test.TestScript

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object TestScenario extends ScenarioObj {
  def apply() = new TestScenario
}

@ShowScenario
class TestScenario extends DebugScenario {
  val script = TestScript
  val testersNum = 1
  val requestsNum = 1
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds

  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
}