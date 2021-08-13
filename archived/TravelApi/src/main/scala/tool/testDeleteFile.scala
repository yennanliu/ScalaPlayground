package tool

// https://www.javatpoint.com/how-to-delete-a-file-in-java
// https://www.w3schools.com/java/java_files_delete.asp

import java.io.File

object testDeleteFile extends App {

  val to_delete_path = "src/main/scala/data/user.json"

  // test 4
  val myFile:File = new File(to_delete_path)
  if (myFile.delete()) println("delete file OK !")
  else println("delete failed !")
}
