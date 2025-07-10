package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{DebugScenario, ScenarioObj}
import scripts.test.RomAnnaHttpsScript
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

object TestHttpsScenario extends ScenarioObj {
  def apply() = new TestHttpsScenario
}

@ShowScenario
class TestHttpsScenario extends DebugScenario {
  val script = RomAnnaHttpsScript
  val testersNum = 10
  val requestsNum = -1
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds

  override val statRequests = true // расчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // расчитывать отклонение
}