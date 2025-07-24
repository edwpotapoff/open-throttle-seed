package scripts.test

import io.ot.*
import io.ot.CheckOperator.*
import io.ot.builder.*
import io.ot.FirstAndAll.*


import scala.concurrent.duration.DurationInt
import scala.language.postfixOps


object TestScript extends ScriptObj {
  def apply() = new TestScript
}

class TestScript extends Script {
  val users = Array("rom", "anna")

  var requests: Array[CO] = _

  def init0() = requests = users.map {
    user => Http(s"/users/$user", "lh").make()
  }


  val assertBooks = () =>
    log.info(s"the titles is: ${"titles".tco[Any]}")
    log.info(s"the titl is: ${"titl".tco[Any]}")

    if (jsonpath("$..title", saveAs = "titls") != null) // путь optional false
      val titls = "titls".list
      titls.foreach(i => log.info(s"the title is: $i"))

    if (jsonpath("$..titl", true, saveAs = "titl") != null) // optional - не вызывать ошибку
      val titls = "titl".list
      titls.foreach(i => log.info(s"the titl is: $i"))


    //jsonpath("$.books[1].year", "==", 1951, "bks1") // сохраняем $.books[2].year в bks1
    //tc("bks0") = jsonpath("$.books[0].year") // будет ошибка, если "$.books[0].year" нет

    asserts


  val assertUser = () => {
    subStr(users(user))
    val s = ";laglsdfdf;lkj"
    if (!subStro(s))
      log.info(s"'$s' - подстроки нет")

    //    val rez = regex(""""([^"]*)"""", Eq, Seq("\"age\"", "\"countryOfResidence\"", "\"Rus\"", "\"name\"", "\"anna\"")) // ==
    //    log.info(s"regex = $rez")

    subStr("\"", "ic")
    val ic = "ic".array
    _assert(ic(0) == 1, "Первая кавычка должна быть сразу после {")
    _assert(ic.size == 10, "Всего кавычек 10")
    _assert(subStrCount("\"") == 10, "Всего кавычек 10")
    _assert(subStrCounto("\"") == 10, "Всего кавычек 10") // не будет ошибки, если кавычек нет, subStrCounto вернет 0

    //_assert("userName".tc == users(user), s"userName != ${users(user)}!")
    statusIs(tc.status)

    bodyStringIs(s"{\"age\":30,\"countryOfResidence\":\"Rus\",\"name\":\"${users(user)}\"}")
    //bodyStringIS("parameters/expected-template.json".ot) // ElFileBody()

    //bodyStringIS("""{"age":30,"countryOfResidence":"Rus","name":"${userName}"}""".st) // StringBody()

    //    if (users(user) == "rom")
    //      bodyBytesIS("parameters/expected-rom.json".bf) // RawFileBody()

    responseTimeLT(700000 microseconds) //

    tc("user") = jsonValue
    val t = "user.age".any
    log.info(s"the user age is $t")
    asserts
  }

  var user = 0
  val seq = List("age", "countryOfResidence", "Rus", "name", "${userName}") // последовательность содержит шаблон, вычисляемый на "лету" в Check
    .map("\"" + _ + "\"") // заключаем каждое слово в кавычки


  val requestUser = () =>
    user = rand(users.size)
    val qUser = s"\"${users(user)}\""
    tc("userName") = users(user)
    requests(user)


  override val init = LoadSteps(init0)


  val load = Load(
    requestUser,
    Regex(""""([^"]*)"""", Eq, seq, "regVar", All), // ищем все записи по шаблону
    // Check(""""([^"]*)"""", Eq, seq), // по умолчанию формат текстовый и проверяются регулярные выражения
    // Check(""""([^"]*)"""", Eq, "abc"), тест на ошибку
    // Save(""""([^"]*)"""" -> "regVar"),
    assertUser,
    Http("/books", "lh"),
    Json,
    Vars(
      "jsp" -> "$.books[0].year",
      "op" -> "<", // Сравнение на меньше. В гатлинге is, lt, lte... невозможно параметризовать
      "val" -> 1926,
      "per" -> "bks0"
    ),
    Save(
      "${jsp}" -> "${per}",
      ("$.books[1].year", "bks1", true) // можно добавить третий параметр, говорящий о том, что путь может не существовать
    ),
    Check(
      ("${jsp}", "${op}", "${val}"),
      ("$.books[0].year", Eq, 1925), // "=="
      ("$.books[0].year", Le, 1925), // "<="
      ("$.books[0].year", Ge, 1925), // ">="
      ("$.books[0].year", Gt, 1924), // >
      ("$.books[0].year", In, Seq(1924, 1925, 1926)),
      "$.books[0].yea" -> NotExists, // нет ненужного третьего аргумента
      ("$.books[2].year", Eq, 1949)
    ),
    assertBooks
  )
}