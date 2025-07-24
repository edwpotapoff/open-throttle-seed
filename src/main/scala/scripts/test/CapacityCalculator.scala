package scripts.test

import io.ot.*
import io.ot.builder.*

class CapacityCalculator extends Script {

  val load = Load(
      () =>
        val str = Library.vars.mkString
        log.info(str)

  )
}