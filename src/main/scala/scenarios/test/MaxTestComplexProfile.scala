package scenarios.test

import io.ot.Max
import io.ot.api.annotation.ShowScenario
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@ShowScenario
class MaxTestComplexProfile extends Max {
  val scenario = TestComplexProfile
  val stepsNumber = 4 // 4
  val stepDuration = 60 seconds
  val startK = 1
  val stepK = 1
}
