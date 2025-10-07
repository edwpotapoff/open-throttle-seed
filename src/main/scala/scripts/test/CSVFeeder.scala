package scripts.test

import io.ot.*
import io.ot.builder.*

object CSVFeeder extends ScriptObj:
  def apply() = new CSVFeeder


class CSVFeeder extends Script:
  val check = () =>
    val username = "username".string
    val city = "city".string
    log.info(s"username $username, city $city, id $hitId")


  val load = Load(
    FeedDs("./parameters/Users", "csv", family = "DataSource0")
    , FeedDs("./parameters/Cities", "csv", family = "DataSource0")
    , check
  )

