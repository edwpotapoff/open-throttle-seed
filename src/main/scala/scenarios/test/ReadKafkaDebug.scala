package scenarios.test

import io.ot.DebugScenario
import io.ot.api.annotation.ShowScenario
import scripts.test.ReadKafka
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@ShowScenario
class ReadKafkaDebug extends DebugScenario {
  val script = ReadKafka
  val testersNum = 10
  val requestsNum = 10000
  val openThrottle = 0 seconds
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}