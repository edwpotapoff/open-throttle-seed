package scripts.test

import io.ot.{Script, ScriptObj, builder}

object ScriptAnna extends ScriptObj {
  def apply() = new ScriptAnna
}

class ScriptAnna extends Script {
  val req =
    """{
         type: "send"
         send.endpoint.url: "http://localhost:8080/users/anna"
         send.endpoint.headers:{
           type: GET
           status: 200
           holder: "rcvdBody"
           keep-alive: true
         }
       }""".http //         timeout: 56 ms


  def step1() = req

  val load = builder.LoadSteps(step1)
}
