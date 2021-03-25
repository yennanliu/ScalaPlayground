package tool

import utils.DataIO

object testDeleteAndMakeFile extends App {

  val Data_io = new DataIO

  val to_delete_path = "src/main/scala/data/user.json"

  println("delete file")
  Data_io.deleteFile(to_delete_path)

  println("make file")
  val userId = "u-001"
  var newUser =  ujson.Obj(userId -> "")

  os.write.over(os.pwd/"src"/"main"/"scala"/"data"/"user.json", newUser)

}
