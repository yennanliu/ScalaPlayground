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

  print("redis = " + redis.toString)
  print("redis = " + redis.host)
  print("redis = " + redis.db)
  print("redis = " + redis.port)
  print("redis = " + redis.name)
  print("redis = " + redis.password)

  val futurePong = redis.ping()
  println("Ping sent!")
  futurePong.map(pong => {
    println(s"Redis replied with a $pong")
  })
  Await.result(futurePong, 5 seconds)

  akkaSystem.terminate()
}
