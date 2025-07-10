package scripts.test

import io.ot._
import io.ot.builder.{Load, LoadSteps}

import scala.{Array => L}


object RomAnnaHttpsScript extends ScriptObj {
  def apply() = new RomAnnaHttpsScript
}

class RomAnnaHttpsScript extends Script {
  val users = L("rom", "anna")

  var requests: L[CO] = _

  def init0() = {
    requests = users.map {
      user =>
        tc("userName") = user
        """{
         type: "send"
         send.endpoint.url: "https://localhost:8080/users/$userName"
         send.endpoint.headers:{
           type: GET
           status: 200
           // holder: "rcvdBody"
           timeout: 700 ms
           keep-alive: true
         }
       }""".http
    }

    empty
  }


  var user = 0 // индекс текущего пользователя

  def assertStep() = {
    assertSubStr(users(user))
    empty
  }

  def step1() = {
    user = rand(1000)
    user %= users.size
    requests(user)
  }

  override val init = LoadSteps(init0)
  val load = Load("Transaction1" -> LoadSteps(step1, assertStep))
}