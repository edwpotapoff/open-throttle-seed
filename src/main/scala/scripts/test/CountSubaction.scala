package scripts.test

import io.ot._
import io.ot.builder.Load


object CountSubaction extends ScriptObj {
  def apply() = new CountSubaction
}

class CountSubaction extends Script {
  val load = Load(
    () => {
      tc("x") = callIndex();
      empty
    }
    , 10 -> Count2Subaction
  )
}