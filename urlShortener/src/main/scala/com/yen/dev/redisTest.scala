package com.yen.dev

// https://github.com/etaty/rediscala
// https://mvnrepository.com/artifact/com.github.etaty/rediscala_2.12/1.8.0

import redis.RedisClient
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object redisTest extends App {

  implicit val akkaSystem = akka.actor.ActorSystem()

  val redis = RedisClient()

  println(" ---> redis = " + redis.toString)
  println("redis.host = " + redis.host)
  println("redis.db = " + redis.db)
  println("redis.port = " + redis.port)
  println("redis.name = " + redis.name)
  println("redis.password = " + redis.password)

  val futurePong = redis.ping()
  println("Ping sent!")
  futurePong.map(pong => {
    println(s"Redis replied with a $pong")
  })
  Await.result(futurePong, 5 seconds)

  akkaSystem.terminate()
}
