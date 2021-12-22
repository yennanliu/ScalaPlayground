package com.yen.dev


import redis.RedisClient
import redis.api.scripting.RedisScript
import redis.protocol.{Bulk, MultiBulk}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/** Demo : get multiple Redis keys with range */

object redisTest5 extends App {

  implicit val akkaSystem = akka.actor.ActorSystem()

  val redis = RedisClient()

  val redisTransaction = redis.transaction()
  val redisScript = RedisScript("keys *")

  val scriptString = redis.evalshaOrEval(redisScript)

  val set = redis.set("key", "scripting")

  val r = for {
    _scriptString <- scriptString
  } yield {
    println(s"script ${redisScript.script} :")
    _scriptString match {
      case b: Bulk => println(b.toString())
      case _ => println("Bulk reply expected!")
    }

    //Await.result(r, 5 seconds)
  }
}