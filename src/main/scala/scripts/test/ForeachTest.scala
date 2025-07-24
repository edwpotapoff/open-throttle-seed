package scripts.test

import io.ot._
import io.ot.builder.Http
import io.ot.builder.{Foreach, Load, LoadSteps}

import scala.language.postfixOps


class ForeachTest extends Script {
  val users = Array("rom", "anna")

  override val init = LoadSteps(() => {
    tc("users") = users
    empty
  })


  var request = () => Http("lh:/users/${user}").make()


  val check = () => {
    subStr("user".tc[String])
    asserts
  }

  val check2 = () => {
    subStr("user".tc[String])
    log.info(s"user ${"user".any}, age ${"age".any}")
    asserts
  }

  val load = Load(
    Foreach(users, "user")(request, check)
    , Foreach(
      Map(
        "user" -> Seq("rom", "anna")
        , "age" -> Seq(31, 32))
    )(request, check2)
    , Foreach("users", "user")(request, check)
  )
}

object ForeachTest extends ScriptObj {

  def apply() = new ForeachTest
}