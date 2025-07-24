package scripts.test

import io.ot.{Script, ScriptObj, builder}

object SqlFeederLocal extends ScriptObj {
  def apply() = new SqlFeederLocal
}

class SqlFeederLocal extends Script {
  var film: LDS = _

  def initStep0() = {
    film = "dvdrental : SELECT * FROM public.film".sql // film_id, title, description
    pass
  }

  def setupStep() = {
    film.next // если rand, то при инициализации на этом шаге соединение будет закрыто и вся таблица будет загружена в память
    // и количество тестиривщиков возможно больше, чем количество соединений к БД!
    pass
  }

  def check0() = {
    val film_id = "film_id".any
    val title = "title".string
    val description = "description".string

    log.info(s"film_id $film_id, title $title, description $description, hitId $hitId")
    empty
  }

  override val init = builder.LoadSteps(initStep0)
  val load = builder.LoadSteps(setupStep, check0)

}