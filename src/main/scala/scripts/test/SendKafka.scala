
package scripts.test

import io.ot.builder.KafkaSend
import io.ot.{Script, ScriptObj, builder}


object SendKafka extends ScriptObj {
  def apply() = new SendKafka
}

class SendKafka extends Script {
  val req = KafkaSend("producer:send-order-event", body = "Привет!", headers = Map(key -> "key", "h" -> "h1")).make()

  //  val reqV2 = Kafka(
  //    Send(
  //      Endpoint("producer:send-order-event"),
  //      Message( "Привет!", Map(key -> "key", "h" -> "h1"))
  //    )
  //  ).make(false)

  //    """{
  //        type: "send"
  //        send.endpoint.url: "producer:send-order-event"
  //        send.message: {
  //            body: "Привет!" // сообщение
  //            headers: {
  //                key: "key"
  //                h: "h1"
  //            }
  //        }
  //    }
  //    """.kafka

  var id = 0

  def assertStep() = {
    assert(tc("producer") == 1, s"Количество отправленных записей должно равняться 1")
    empty
  }


  def step1() = {
    id += 1
    req.copy(key = s"key$id", body = s"Привет key$id!")
  }

  val load = builder.LoadSteps(step1, assertStep)
}
