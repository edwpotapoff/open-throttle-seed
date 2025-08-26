package scenarios.test

import io.ot.DebugScenario
import scripts.test.HelloWorldGrpc

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


class HelloWorldGrpcDebug extends DebugScenario {
  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение
  val script = HelloWorldGrpc
  val testersNum = 100
  val requestsNum = 10000
  val openThrottle = 100 millis
  val runImmediately = false // after opening
  val statPeriod = 10 seconds
}


