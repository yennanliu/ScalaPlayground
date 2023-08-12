package org.akka.essentials.scala.stm.transactor.example

import akka.actor.SupervisorStrategy._
import akka.actor.Actor
import akka.actor.AllForOneStrategy
import akka.actor.Props
import akka.transactor.CoordinatedTransactionException
import akka.util.Timeout
import scala.concurrent.Await
import akka.actor.ActorLogging
import akka.pattern.ask
import scala.concurrent.duration._


class BankActor extends Actor with ActorLogging {

  val transferActor = context.actorOf(Props[TransferActor], name = "TransferActor")
  implicit val timeout = Timeout(5 seconds)

  def receive = {
    case transfer: TransferMsg =>
      transferActor ! transfer
    case balance: AccountBalance =>
      val future = ask(transferActor, balance).mapTo[AccountBalance]
      val account = Await.result(future, timeout.duration)
      log.info("Account #{} , Balance {}", account.accountNumber,
        account.accountBalance)
  }

  override val supervisorStrategy = AllForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 10 seconds) {
    case _: CoordinatedTransactionException => Resume
    case _: IllegalStateException => Resume
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }
}