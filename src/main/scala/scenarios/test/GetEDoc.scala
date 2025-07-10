package scenarios.test

import io.ot.FunctionalScenario
import io.ot.api.annotation.ShowScenario
import scripts.test.GetEDocUFEBSSynapse

@ShowScenario
class GetEDoc extends FunctionalScenario {
  val script = GetEDocUFEBSSynapse
  override val requestsNum = -1 // -1 бесконечный цикл
}