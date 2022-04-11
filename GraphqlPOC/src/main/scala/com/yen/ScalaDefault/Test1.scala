package com.yen.ScalaDefault

object Test1 extends App {

//  // example 1
//  val url = "http://localhost:8080/graphql"
//  val r = requests.get(url)
//  println(r.statusCode)

  // example 2
  val url2 = "http://localhost:8080/graphql"
  val data2 = "{ \"query\": \"{ allPeople { id } } \" }"
  val r2 = requests.post(
    url2,
    data = data2,
    headers = Map(
      "Content-Type" -> "application/json"
    )
  )
  println(r2.statusCode)
  println(r2.contents.toString())
  println(r2.contents.foreach(println(_)))
}
