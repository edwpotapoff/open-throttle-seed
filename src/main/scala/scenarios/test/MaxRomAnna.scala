package scenarios.test


import io.ot.Max
import io.ot.api.annotation.ShowScenario
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@ShowScenario
class MaxRomAnna extends Max {
  val scenario = RomAnnaDebug
  val stepsNumber = 1
  val stepDuration = 30 seconds
  val startK = 1
  val stepK = 1
}
