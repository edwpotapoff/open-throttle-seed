package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.util.Base64
import scala.concurrent.duration._

class TestHttp2 extends Simulation {

  // 1. Конфигурация HTTP
  val httpProtocol = http
    .baseUrl("https://127.0.0.1:8443")
    .acceptHeader("*/*")
    .userAgentHeader("Gatling/3.6.1")
    .disableFollowRedirect // или .enableFollowRedirect если нужно следовать редиректам
    .enableHttp2
    .disableAutoReferer
    .disableCaching
    .inferHtmlResources()
    .silentResources // Для подавления логов запросов к ресурсам


  val req = http("GET Request")
    .get("/")
    .check(
      status.is(200),
      bodyString.saveAs("resp")
    )

  // 3. Определение сценария
  val scn = scenario("Http/2 Test").exec(req).exec { tc =>
    println(s"Response: ${tc("resp")}")
    tc
  }

  setUp(
    scn.inject(
      rampUsers(1) during (1.seconds)
    )
  ).protocols(httpProtocol)
}