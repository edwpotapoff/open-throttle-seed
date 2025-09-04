package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{DebugScenario, ScenarioObj}
import scripts.test.EmptyScript

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object EmptyDebug extends ScenarioObj {
  def apply() = new EmptyDebug
}

@ShowScenario
class EmptyDebug extends DebugScenario {
  val script = EmptyScript
  val testersNum = 100
  val requestsNum = 300_000
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds

  override val statRequests = false // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = false // рассчитывать отклонение
}