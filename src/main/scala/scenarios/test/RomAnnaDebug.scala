package scenarios.test

import io.ot.{DebugScenario, ScenarioObj}
import io.ot.api.annotation.ShowScenario
import scripts.test.RomAnnaScript

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


@ShowScenario
class RomAnnaDebug extends DebugScenario {
  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
  val script = RomAnnaScript
  val testersNum = 1
  val requestsNum = 1
  val openThrottle = 10 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}

object RomAnnaDebug extends ScenarioObj {
  def apply() = new RomAnnaDebug
}
