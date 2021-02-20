package utils

// https://mungingdata.com/scala/read-write-json/

class DataIO{

  def getUserData(): Unit = {
    os.read
    val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"user.json")
    val data = ujson.read(userJson)
    println(data.value)

  }
}

object test extends App {
  val d = new DataIO
  d.getUserData()
}
