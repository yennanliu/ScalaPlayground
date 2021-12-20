package com.yen.dev

// https://github.com/etaty/rediscala-demo/blob/master/src/main/scala/ExampleTransaction.scala

import akka.util.ByteString
import redis.RedisClient

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/** Demo : add Redis record with key, value */

object redisTest3 extends App {

  implicit val akkaSystem = akka.actor.ActorSystem()

  val redis = RedisClient()

  val redisTransaction = redis.transaction()

  redisTransaction.watch("key1")
  val set = redisTransaction.set("key1", "zzzz")
  val decr = redisTransaction.decr("key1")
  val get = redisTransaction.get("key1")

  redisTransaction.exec()
  val r = for {
    s <- set
    g <- get
  } yield {
    assert(s)
    println("s = " + s)
    println("g = " + g.foreach(println(_)) + g.get(0))
  }

  decr.onFailure({
    case error => println(s"decr failed : $error")
  })

  Await.result(r, 10 seconds)

  akkaSystem.terminate()
}