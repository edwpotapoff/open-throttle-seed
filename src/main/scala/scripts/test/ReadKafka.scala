package scripts.test

import io.ot.builder.KafkaReceive
import io.ot.{Script, ScriptObj, builder}


object ReadKafka extends ScriptObj {
  def apply() = new ReadKafka
}

class ReadKafka extends Script {
  val req =
    KafkaReceive(
      "consumer:send-order-event"
      , Map("timeout" -> 10, "connectTimeout" -> "10 s")
    ).make()

  //  Kafka(
  //    Receive(
  //      Endpoint("consumer:send-order-event", Map(timeout -> 10, connectTimeout -> "10 s"))
  //    )
  //  ).make(false)

  //    """{
  //        type: "receive"
  //        receive.endpoint.url: "consumer:send-order-event"
  //        receive.endpoint.headers: {
  //            timeout: 10 ms
  //            connectTimeout: 10 s
  //        }
  //    }
  //    """.kafka

  var group = ""

  def initGroup() = {
    group = s"group$testerNum"
    log.info(s"group = $group")
    empty
  }

  def step1() = {
    req.copy(group = group)
  }

  def assertStep() = {
    assertSubStr("Привет")
    empty
  }


  override val init = builder.LoadSteps(initGroup)
  val load = builder.LoadSteps(step1, assertStep)
}
