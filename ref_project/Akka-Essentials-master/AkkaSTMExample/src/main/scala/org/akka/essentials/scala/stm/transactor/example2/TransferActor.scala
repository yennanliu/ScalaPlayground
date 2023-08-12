package org.akka.essentials.scala.stm.transactor.example2

import akka.actor.SupervisorStrategy._
import akka.actor.Actor
import akka.actor.AllForOneStrategy
import akka.actor.Props
import akka.transactor.Coordinated
import akka.transactor.CoordinatedTransactionException
import scala.concurrent.duration._
import akka.util.Timeout

class TransferActor extends Actor {

  val fromAccount = "XYZ"
  val toAccount = "ABC"

  val from = context.actorOf(Props(new AccountActor(fromAccount, 5000)), name = fromAccount)
  val to = context.actorOf(Props(new AccountActor(toAccount, 1000)), name = toAccount)
  implicit val timeout = Timeout(5 seconds)

  def receive: Receive = {
    case message: TransferMsg =>
      val coordinated = Coordinated()
      coordinated atomic {
        implicit t =>
          from ! coordinated(new AccountDebit(
            message.amtToBeTransferred))
          to ! coordinated(new AccountCredit(
            message.amtToBeTransferred))
      }
    case message: AccountBalance =>
      if (message.accountNumber.equalsIgnoreCase(fromAccount))
        from.tell(message, sender)
      else if (message.accountNumber.equalsIgnoreCase(toAccount))
        to.tell(message, sender)

    case message: AccountDebit =>
      from.tell(message, sender)
    case message: AccountCredit =>
      from.tell(message, sender)
  }

  override val supervisorStrategy = AllForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 10 seconds) {
    case _: CoordinatedTransactionException => Resume
    case _: IllegalStateException => Resume
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }
}