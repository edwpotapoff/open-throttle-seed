package scripts.test

import io.ot.*
import io.ot.builder.{Http, LoadSteps}

import scala.language.postfixOps


class RomAnnaScript extends Script {
  val users = Array("rom", "anna")
  var requests: Array[CO] = _

  def fillRequests() = {
    requests = users.map {
      user =>
        Http(s"lh:/users/$user")
          .make()
    }
    empty
  }


  var user = 0

  def doRequest() = {
    user = rand(users.size)
    tc("userName") = users(user)
    requests(user)
  }

  def assertStep() = {
    subStr(users(user))
    //bodyStringIS("parameters/expected-template.json".ot) // ElFileBody()
    //bodyStringIS("""{"age":30,"countryOfResidence":"Rus","name":"${userName}"}""".st) // StringBody()
    asserts
  }

  override val init = LoadSteps(fillRequests)
  val load = LoadSteps(
    () =>
      log.info(s"name = ${RomAnnaScript.name}")
    , doRequest
    , assertStep
  )
}

object RomAnnaScript extends ScriptObj {
  def apply() = new RomAnnaScript
}