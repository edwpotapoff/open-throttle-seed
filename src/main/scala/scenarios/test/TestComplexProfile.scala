package scenarios.test

import io.ot.api.annotation.ShowScenario
import io.ot.{ComplexProfile, ScenarioObj}
import scripts.test.{ScriptAnna, ScriptRom}
import scala.language.postfixOps
import scala.concurrent.duration.DurationInt

object TestComplexProfile extends ScenarioObj {
  def apply() = new TestComplexProfile
}

@ShowScenario
class TestComplexProfile extends ComplexProfile {
  val testersNum = 10 // Базовое количество пользователей. Если оно 0 или меньше расчётного, то берется расчетное количество пользователей
  val requestsNum = 10 // количество пробных шагов
  val openThrottle = 10 milli // Задержка между открытием новых тестировщиков. Отрицательное значение - случайное значение в пределай пейсинга, а на этапе пробы значение по модулю
  val runImmediately = false // after opening
  val pacingReserve = 0.3 // расчет количества пользователей на сценарий так, чтобы пауза в пейсинге составляла 30% времени
  val statPeriod = 10 seconds // период обработки статистики

  override val statRequests = true // рассчитывать статистику по запросам (а не только по транзакциям)
  override val statDeviation = true // рассчитывать отклонение


  val loadScripts = Set(
    ScriptRom
    , ScriptAnna
  )

  // Отражение Имя транзакции -> RPS
  val loadProfile = Map(
    ScriptRom -> 10 // 97D / 5
    , ScriptAnna -> 20 //73D / 5
  )
}