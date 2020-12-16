package example

import scala.concurrent.duration._

object BasicExample extends App {
  println("Application started")
  println("Sleeping...")
  
  Thread.sleep(2.seconds.toMillis)

  for (i <- 1 to 10)
    println (i)

  var x = 0

  while (true){
    println (x)
    Thread.sleep(1000)
    x += 1
  }

  println("Application exiting")
}