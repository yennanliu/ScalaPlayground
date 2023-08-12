package org.akka.essentials.supervisor.example2
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.ask
import akka.util.duration._
import akka.dispatch.Await
import akka.util.Timeout
import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.OneForOneStrategy
import akka.dispatch.Future
import akka.actor.SupervisorStrategy._
import akka.actor.ActorRef

case class Result

object MyActorSystem {
  
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("faultTolerance")
    val log = system.log
    val originalValue: Int = 0

    val supervisor = system.actorOf(Props[SupervisorActor], name = "supervisor")

    log.info("Sending value 8, no exceptions should be thrown! ")
    var mesg: Int = 8
    supervisor ! mesg

    implicit val timeout = Timeout(5 seconds)
    var future = (supervisor ? new Result).mapTo[Int]
    var result = Await.result(future, timeout.duration)

    log.info("Value Received-> {}", result)

    log.info("Sending value -8, ArithmeticException should be thrown! Our Supervisor strategy says resume !")
    mesg = -8
    supervisor ! mesg

    future = (supervisor ? new Result).mapTo[Int]
    result = Await.result(future, timeout.duration)

    log.info("Value Received-> {}", result)

    log.info("Sending value null, NullPointerException should be thrown! Our Supervisor strategy says restart !")
    supervisor ! new NullPointerException

    future = (supervisor ? new Result).mapTo[Int]
    result = Await.result(future, timeout.duration)

    log.info("Value Received-> {}", result)

    log.info("Sending value \"String\", IllegalArgumentException should be thrown! Our Supervisor strategy says Stop !")

    supervisor ? "Do Something"

    log.info("Worker Actors shutdown !")

    system.shutdown
  }

}