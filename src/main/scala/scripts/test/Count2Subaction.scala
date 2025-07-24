package scripts.test

import io.ot.{Script, ScriptObj, builder}


object Count2Subaction extends ScriptObj {
  def apply() = new Count2Subaction
}

class Count2Subaction extends Script {
  var count = 0

  def chek() = {
    count += 1
    val x = "x".any
    log.info(s"chek $x: $count ${callIndex()}")
    empty
  }

  val load = builder.LoadSteps(chek)
}