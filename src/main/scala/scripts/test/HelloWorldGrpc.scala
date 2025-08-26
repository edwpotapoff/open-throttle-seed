package scripts.test

import com.example.helloworld.{GreeterService, HelloReply, HelloRequest}
import io.ot.api.*
import io.ot.builder.*
import io.ot.{Script, ScriptObj}


class HelloWorldGrpc extends Script:
  val load = Load(
    () =>
      OTGrpc(
        componentName = "helloworld",
        method = GreeterService.MethodDescriptors.sayHelloDescriptor,
        payload = HelloRequest("Полина")
      )
    , () =>
      // ответ типа GrpcResponse[HelloReply] записывается в контекстную переменную "helloworld"
      val hw: GrpcResponse[HelloReply] = tc("helloworld")
      log.info(s"Ответ: ${hw.response.message}")
  )


object HelloWorldGrpc extends ScriptObj:
  def apply() = new HelloWorldGrpc

