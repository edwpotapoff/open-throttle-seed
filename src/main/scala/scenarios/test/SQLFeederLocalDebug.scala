//package scenarios.test
//
//import io.ot.DebugScenario
//import io.ot.api.annotation.ShowScenario
//import scripts.test.SqlFeederLocal
//import scala.language.postfixOps
//import scala.concurrent.duration.DurationInt
//
//@ShowScenario
//class SQLFeederLocalDebug extends DebugScenario {
//  val script = SqlFeederLocal
//  val testersNum = 2
//  val requestsNum = 2
//  val openThrottle = 100 millis
//  val runImmediately = false // after opening
//  val statPeriod = 10 seconds
//}