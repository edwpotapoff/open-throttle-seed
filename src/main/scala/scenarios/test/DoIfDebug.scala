package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{DebugScenario, ScenarioObj}
import scripts.test.{DoIfTest, TestScript}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object DoIfDebug extends ScenarioObj {
  def apply() = new DoIfDebug
}

@ShowScenario
class DoIfDebug extends DebugScenario {
  val script = DoIfTest
  val testersNum = 1
  val requestsNum = 10
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds

  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
}