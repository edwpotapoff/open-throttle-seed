package scenarios.test

import io.ot.{DebugScenario, ScenarioObj}
import io.ot.api.annotation.ShowScenario
import scripts.test.ScriptAnna
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

object AnnaScenario extends ScenarioObj {
  def apply() = new AnnaScenario
}

@ShowScenario
class AnnaScenario extends DebugScenario {
  val script = ScriptAnna
  val testersNum = 100
  val requestsNum = 100
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}