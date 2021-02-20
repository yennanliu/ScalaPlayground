package utils

// https://mungingdata.com/scala/read-write-json/
// https://github.com/lihaoyi/os-lib#getting-started

class DataIO{

  def getUserRecord() = {
    os.read
    val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"user.json")
    val data = ujson.read(userJson)
    data
  }

  def getOrderRecord() = {
    os.read
    val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"order.json")
    val data = ujson.read(userJson)
    data
  }
}

//object test extends App {
//  val d = new DataIO
//  val users = d.getUserRecord()
//  println(users.getClass)
//  println("***" + users("name"))
//  d.getOrderRecord()
//}
