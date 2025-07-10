package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{DebugScenario, ScenarioObj}
import scripts.test.{HttpTest, ScriptWs}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object HttpScenario extends ScenarioObj {
  def apply() = new HttpScenario
}

@ShowScenario
class HttpScenario extends DebugScenario {
  val script = HttpTest
  val testersNum = 10
  val requestsNum = 10
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds

  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
}