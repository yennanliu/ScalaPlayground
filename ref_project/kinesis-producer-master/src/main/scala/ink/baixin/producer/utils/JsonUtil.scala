package ink.baixin.producer.utils

import org.json4s._
import org.json4s.native.Serialization._
import org.json4s.native.Serialization

object JsonUtil {
  implicit val formats = Serialization.formats(NoTypeHints)

  // parse the query parameters of HTTP GET to Json String
  def parameter2Json(params: String) = {
    val paramMap = params.split("&").map{ str =>
      if (str.contains("=")) {
        (str.split("=")(0), str.substring(str.indexOf("=") + 1))
      } else {
        (str, "")
      }
    }.toMap
    write(paramMap)
  }

  // parse map to json
  def map2Json(map: Map[String, String]) = write(map)
}
