package com.yen.dev

import akka.util.ByteString
import redis.RedisClient

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/** Demo : delete Redis record with key */

object redisTest4 extends App {

  implicit val akkaSystem = akka.actor.ActorSystem()

  val redis = RedisClient()

  val redisTransaction = redis.transaction()

  redisTransaction.watch("key1")
  val del = redisTransaction.del("key1")

  redisTransaction.exec()
  val r = for {
    d <- del
  } yield {
    println("d = " + d)
  }

  Await.result(r, 10 seconds)

  akkaSystem.terminate()
}