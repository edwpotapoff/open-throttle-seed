package scripts.test

import io.ot.builder.*
import io.ot.*

object SqlFeeder extends ScriptObj:
  def apply() = new SqlFeeder


class SqlFeeder extends Script:
  var film: FDS = _

  def openDs(): CO = 
    film = "dvdrental : SELECT film_id, title, description FROM public.film".fsql("DataSource0", k = 1)
    pass // будет ожидаться к открытию один источник данных

  def setupStep(): CO = 
    film.rand // nextUnique
    pass

  val check = () => 
    val film_id = "film_id".any
    val title = "title".string
    val description = "description".string

    log.info(s"film_id $film_id, title $title, description $description, hitId $hitId")

//  override val init = LoadSteps(openDs)
  val load = Load(
    FeedDs("dvdrental : SELECT film_id, title, description FROM public.film", "sql", 1, "DataSource0", true) //setupStep
    , check
  )
