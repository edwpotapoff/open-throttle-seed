package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{DebugScenario, ScenarioObj}
import scripts.test.ScriptWs

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object WsDebug extends ScenarioObj {
  def apply() = new WsDebug
}

@ShowScenario
class WsDebug extends DebugScenario {
  val script = ScriptWs
  val testersNum = 1
  val requestsNum = 1
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds

  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
}