package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{Feed, Load, LoadSteps}

import scala.language.postfixOps


class FeedTest extends Script {
  val users = Array("rom", "anna")

  override val init = LoadSteps(() => {
    tc("users") = users;
    empty
  })


  var request = () => Http("lh:/users/${user}").make()


  val check = () => {
    subStr("user".tc.string)
    asserts
  }


  val feeder = Array(
    Map("user" -> "rom"),
    Map("user" -> "anna")
  ).iterator

  val load = Load(
    Feed(feeder)
    , request
    , check
  )
}

object FeedTest extends ScriptObj {
  def apply() = new FeedTest
}