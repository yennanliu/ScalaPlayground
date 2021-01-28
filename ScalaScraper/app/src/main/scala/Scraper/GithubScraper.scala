package Scraper

// https://github.com/yennanliu/utility_Scala/blob/master/src/main/scala/ScalaBasic/CompanionDemo3.scala

class GithubScraper {

  private val baseURL = "https://github.com/"
  def say(): Unit = {
    println("person")
  }
}

object GithubScraper {

  def ParseText(userName:String):String ={
    val url = "https://github.com/" + userName
    val response = requests.get(url).text()
    response
  }
}
