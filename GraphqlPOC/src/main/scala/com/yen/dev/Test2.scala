package com.yen.dev

// https://github.com/sangria-graphql/sangria

import sangria.schema._
import sangria.execution._
import sangria.macros._
import sangria.marshalling.circe._
import scala.concurrent.ExecutionContext.Implicits.global

object Test2 extends App {

  val QueryType = ObjectType("Query", fields[Unit, Unit](
    Field("hello", StringType, resolve = _ => "Hello world!")
  ))

  val schema = Schema(QueryType)

  val query = graphql"{ hello }"

  val result = Executor.execute(schema, query)

  println("=========")
  result.foreach(res => println(res.spaces2))
  //result.foreach(res => println(res.toString))
  println("=========")
}
