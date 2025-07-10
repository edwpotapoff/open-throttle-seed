package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class TestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .disableCaching
    .disableWarmUp
    .connectionHeader("keep-alive")

  val getScenario = scenario("BasicSimulation")
    .group("rom")(
      exec(
        http("http://localhost:8080/users/rom")
          .get("/users/rom")
          .check(status.is(200))
      )
    )


  setUp(
    getScenario.inject(
      //rampUsers(2) during (1 seconds),
      //constantConcurrentUsers(5) during (5 seconds)
      //constantUsersPerSec(10).during(5.seconds)
      atOnceUsers(1)
    )
  ).protocols(httpProtocol)
}