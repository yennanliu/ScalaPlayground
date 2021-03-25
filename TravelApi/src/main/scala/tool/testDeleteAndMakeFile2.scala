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

  //os.write.over(os.pwd/"src"/"main"/"scala"/"data"/"user.json", newUser)

}
