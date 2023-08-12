package org.akka.essentials.scala.dispatcher.example.Dispatcher
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.actor.Props
import org.akka.essentials.scala.dispatcher.MsgEchoActor
import akka.routing.RoundRobinRouter

object Example1 {
	def main(args: Array[String]): Unit = {}
	val _system = ActorSystem.create("default-dispatcher",ConfigFactory.load().getConfig("MyDispatcherExample"))

	val actor = _system.actorOf(Props[MsgEchoActor].withDispatcher("defaultDispatcher").withRouter(
						RoundRobinRouter(5)))

	0 to 25 foreach {
		i => actor ! i
	}
  Thread.sleep(3000)
	_system.shutdown()
}