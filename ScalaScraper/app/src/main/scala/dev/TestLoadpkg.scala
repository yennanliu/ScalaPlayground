package dev

// https://github.com/lihaoyi/requests-scala#passing-in-parameters

object TestLoadpkg extends App{

  //println(requests.getClass())

  val r1 = requests.get("https://github.com/yennanliu/KafkaHelloWorld")
  println(r1.statusCode)
  println(r1.headers)
  println(r1.text())

  val r2 = requests.get(
    "http://httpbin.org/get",
    params = Map("key1" -> "value1", "key2" -> "value2")
  )

  //println(r2)

  val r3 = requests.get("https://www.google.co.jp/")
  println(r3)

  val r4  = requests.get("https://api.github.com/events")
//
//  ujson
//
//  val json = ujson.read(r.text)

}
