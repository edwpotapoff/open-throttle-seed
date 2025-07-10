package scripts.test

import io.ot.{Script, ScriptObj, builder}

object SqlFeeder extends ScriptObj {
  def apply() = new SqlFeeder
}

class SqlFeeder extends Script {
  var film: FDS = _

  def initStep0() = {
    film = "dvdrental : SELECT film_id, title, description FROM public.film".fsql("DataSource0")
    pass
  }

  def setupStep() = {
    film.rand // nextUnique
    pass
  }

  def check0() = {
    val film_id = "film_id".tc
    val title = "title".tc
    val description = "description".tc

    log.info(s"film_id $film_id, title $title, description $description, hitId $hitId")
    empty
  }

  override val init = builder.LoadSteps(initStep0)
  val load = builder.LoadSteps(setupStep, check0)

}