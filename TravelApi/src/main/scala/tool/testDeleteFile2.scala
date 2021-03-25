package tool

import utils.DataIO

object testDeleteFile2 extends App {

  val Data_io = new DataIO

  val to_delete_path = "src/main/scala/data/user.json"

  Data_io.deleteFile(to_delete_path)
}
