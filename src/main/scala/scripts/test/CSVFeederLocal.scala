package scripts.test

import io.ot.builder.{Load, LoadSteps}
import io.ot.{Script, ScriptObj, builder}

object CSVFeederLocal extends ScriptObj {
  def apply() = new CSVFeederLocal
}

class CSVFeederLocal extends Script {
  var users: LDS = _
  var cities: LDS = _

  val initStep = () =>
    users = "./parameters/Users".csv
    cities = "./parameters/Cities".csv(false)
    pass


  def setupStep() = {
    users.next // nextUnique
    cities.next // nextUnique
    pass
  }

  def check0() = {
    val username = tc("username")
    val city = "city".tc
    log.info(s"username $username, city $city, id $hitId")
    empty
  }

  override val init = Load(initStep)

  val load = LoadSteps(setupStep, check0)

}