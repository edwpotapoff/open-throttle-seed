package scripts.test

import io.ot.*
import io.ot.builder.*

object CSVFeeder extends ScriptObj:
  def apply() = new CSVFeeder


class CSVFeeder extends Script:
  var users: FDS = _
  var cities: FDS = _

  val initStep0 = () =>
    users = "./parameters/Users".fcsv("DataSource0")
    cities = "./parameters/Cities".fcsv("DataSource0")
    pass // будет ожидаться открытие двух источников данных

  def setupStep(): CO =
    users.next
    cities.rand // nextUnique
    pass // будут ожидаться данные от источников

  def check0(): CO =
    val username = tc[String]("username")
    val city = "city".any
    log.info(s"username $username, city $city, id $hitId")
    empty

  override val init = Load(initStep0)
  val load = LoadSteps(setupStep, check0)
