package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{Max, Threshold}
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

@ShowScenario
class MaxWithThreshold extends Max with Threshold {
  val scenario = TestScenario
  val stepsNumber = 4
  val stepDuration = 60 seconds
  val startK = 1
  val stepK = 1

  def threshold() = {
    val maxMean = 0.67
    val maxPR95 = 0.93
    val maxErrors = 1
    // вторым аргументом stat можно уточнить имя транзакции, иначе статистика по всему сценарию
    Array(
      (stat("mean") > maxMean,
        s"Превышено среднее время ответа $maxMean мс!"),
      (stat("95") > maxPR95 || stat("countError") > maxErrors,
        s"Превышен 95% процентиль времени ответа в $maxPR95 мс либо ошибок > $maxErrors!")
    )
  }
}