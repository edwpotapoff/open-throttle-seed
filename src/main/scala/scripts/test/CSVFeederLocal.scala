package scripts.test

import io.ot.builder.{FeedDs, Load}
import io.ot.{Script, ScriptObj, builder}

object CSVFeederLocal extends ScriptObj:
  def apply() = new CSVFeederLocal


class CSVFeederLocal extends Script:
  val check = () =>
    val username = "username".string
    val city = "city".string
    log.info(s"username $username, city $city, id $hitId")
  

  val load = Load(
      FeedDs("./parameters/Users", "csv")
    , FeedDs("./parameters/Cities", "csv")
    , check
  )

