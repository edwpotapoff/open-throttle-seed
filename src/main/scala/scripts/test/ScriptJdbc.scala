package scripts.test

import io.ot.builder.{Load, SqlSelect}
import io.ot.{Dict, Script, ScriptObj, builder}

import scala.collection.mutable.ArrayBuffer

object ScriptJdbc extends ScriptObj {
  def apply() = new ScriptJdbc
}

class ScriptJdbc extends Script {

  var id = 0
  val setId = () => {
    id = (id + 1)%1000
    tc("id") = id
  }

  val assertStep = () => {
    val rs: ArrayBuffer[Dict[String, Any]] = tc("dvdrental")
    val h = rs.head
    assert(rs.size == 1, "Набор должен содержать только одну запись")
    assert(h("FILM_ID") == id, s"film_id должен равняться $id")
    assert("FILM_ID".int == id, s"film_id должен равняться $id")
    empty
  }



  val load = Load(
    setId
    , SqlSelect("dvdrental", "SELECT * FROM public.film where film_id = ${id}", "film")
    , assertStep
  )
}
