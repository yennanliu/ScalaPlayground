package Scraper

// https://github.com/yennanliu/utility_Scala/blob/master/src/main/scala/ScalaBasic/CompanionDemo3.scala

class GithubScraper(userName:String) {
  private val baseURL = "https://github.com/"

  def say(): Unit = {
    println("GithubScraper")
  }
}

object GithubScraper {

  def ParseText(userName:String):String ={
    val url = "https://github.com/" + userName
    val response = requests.get(url).text()
    response
  }

  def ParseEvent() ={
    val url = "https://api.github.com/events"
    val r_github = requests.get(url)
    val r_json = ujson.read(r_github.text)
    r_json
  }
}
