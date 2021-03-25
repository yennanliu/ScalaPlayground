package tool

import java.io.File

object testDeleteFile extends App {

  val to_delete_path = "src/main/scala/data/user.json"

  // test 4
  val myFile:File = new File(to_delete_path)
  if (myFile.delete()) println("delete file OK !")
  else println("delete failed !")
}
