package scripts.test

import io.ot.builder.LoadSteps
import io.ot.{Script, ScriptObj, builder}

object ScriptGatlingRom extends ScriptObj {
  def apply() = new ScriptGatlingRom
}

class ScriptGatlingRom extends Script {
  val req =
    """{
       type: "send"
       send.endpoint.url: "http://localhost:8080/users/rom"
       send.endpoint.headers:{
         type: GET
         status: 200
         holder: "rcvdBody"
         keep-alive: true
       }
     }""".http //         timeout: 56 ms


  def step1() = req

  val load = LoadSteps(step1)
}
