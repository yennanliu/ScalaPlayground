package com.yen.ScalaDefault

object Test1 extends App {

  // example 1
  val url = "http://localhost:8080/graphql"
  val r = requests.get(url)
  println(r.statusCode)

  println("=======================")

  // example 2
  val url2 = "http://localhost:8080/graphql"
  //val data2 = "{ \"query\": \"{ allPeople { id } } \" }"
  val data2 = "{ \"query\": \"{ allPeople { id fullName } } \" }"
  val r2 = requests.post(
    url2,
    data = data2,
    headers = Map(
      "Content-Type" -> "application/json"
    )
  )
  println(r2.statusCode)
//  println(r2.contents.toString())
//  println(r2.contents.foreach(println(_)))
  println(r2.text)

  println("=======================")

  // example 3
  val url3 = "http://localhost:8080/graphql"
  val data3 = "{ \"query\" : \" { allPeople { id fullName { friends { id } } } } \" }"
  val r3 = requests.post(
    url3,
    data = data3,
    headers = Map(
      "Content-Type" -> "application/json"
    )
  )
  println(r3.statusCode)
  println(r3.text)
}
