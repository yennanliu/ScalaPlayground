package tool

import utils.DataIO

import scala.collection.mutable.LinkedHashMap

object testDeleteAndMakeFile2 extends App {

  val Data_io = new DataIO

  val to_delete_path = "src/main/scala/data/user.json"
  //
  //  println("delete file")
  //  Data_io.deleteFile(to_delete_path)
  //
  //  println("make file")
  //  val userId = "u-001"
  //  var newUser =  ujson.Obj(userId -> "")

  // https://stackoverflow.com/questions/11386172/workaround-for-prepending-to-a-linkedhashmap-in-scala
  def prepend[K,V](map: LinkedHashMap[K,V], kv: (K, V)) = {
    val copy = map.toMap
    map.clear
    map += kv
    map ++= copy
  }

  val map = LinkedHashMap("a"-> "")
  prepend(map, "b"-> "")
  println(map)

  for (item <- map){
    println(item)
  }

  /** test to load blank json, and add userID to it and save to user.json */
  os.read
  val userJson = os.read(os.pwd/"src"/"main"/"scala"/"data"/"user_default.json")
  val data = ujson.read(userJson)

  println("data = " + data)

  // TODO : check if there is better mechanism
  // overwrite the file if already existed
  val userId = "xxx"
  val newUser =  ujson.Obj(userId -> "")
  data(userId) = newUser
  os.write.over(os.pwd/"src"/"main"/"scala"/"data"/"user.json", data)
}
