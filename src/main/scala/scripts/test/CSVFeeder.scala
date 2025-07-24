package scripts.test

import io.ot.builder.{Load, LoadSteps}
import io.ot.{Script, ScriptObj, builder}

object CSVFeeder extends ScriptObj {
  def apply() = new CSVFeeder
}

class CSVFeeder extends Script {
  var users: FDS = _
  var cities: FDS = _

  val initStep0 = () =>
    users = "./parameters/Users".fcsv("DataSource0")
    cities = "./parameters/Cities".fcsv("DataSource0")
    pass


  def setupStep() = {
    users.next
    cities.rand // nextUnique
    pass
  }

  def check0() = {
    val username = tc[String]("username")
    val city = "city".any
    log.info(s"username $username, city $city, id $hitId")
    empty
  }

  override val init = Load(initStep0)
  val load = LoadSteps(setupStep, check0)
}