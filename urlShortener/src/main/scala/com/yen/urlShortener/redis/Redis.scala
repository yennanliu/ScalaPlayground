//package com.yen.urlShortener.redis
//
///** Redis client object */
//
//import redis.RedisClient
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//import scala.concurrent.ExecutionContext.Implicits.global
//
//class Redis {
//
//}
//
//object Redis{
//
//  // attr
//  var res = true
//
//  implicit val akkaSystem = akka.actor.ActorSystem()
//
//  val redis = RedisClient()
//  val redisTransaction = redis.transaction()
//
//  // method
//  def putValue(key:String, value: String):Boolean={
//
//    redis.set(key, value)
//    val get = redisTransaction.get(key)
//
//    get.onFailure({
//      case error => {
//        println(s"put into Redis failed : $error")
//        res = false
//      }
//    })
//
//    res
//  }
//
//  def deleteValue(key:String):Unit={
//
//    val del = redisTransaction.del(key)
//
//    println(s"delete key : $key ...")
//
//    redisTransaction.exec()
//
//    val r = for {
//      d <- del
//    } yield {
//      println("d = " + d)
//    }
//
//    Await.result(r, 1 seconds)
//    akkaSystem.terminate()
//  }
//
////  def getKeys():Unit={
////    val res = redisTransaction.getrange("*", 0, 100L)
////  }
//
//}