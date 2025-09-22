package scripts.test

import io.ot.builder.*
import io.ot.*

object SqlFeederLocal extends ScriptObj:
  def apply() = new SqlFeederLocal


class SqlFeederLocal extends Script:
  var film: LDS = _

  override val init = Load(() =>
    film = "dvdrental : SELECT * FROM public.film".sql // film_id, title, description
  )

  val load = Load(
    () =>
      film.next
      pass
    , emptyFun // () => ()
  )
