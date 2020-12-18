package utils

// https://scala-slick.org/doc/1.0.1/introduction.html#what-is-slick

import schema.CoffeeSchema.Coffees
import slick.driver.SQLiteDriver.api._

import scala.concurrent.Await
import scala.concurrent.duration._

class SQLUtils {

  val db = Database.forConfig("coffees")
  val coffees = TableQuery[Coffees]

  def getDataCount(): Vector[Int] = {
    val count = Await.result(db.run(sql"""select count(*) from coffees""".as[(Int)]), Duration.Inf)
    count
  }

  def queryDB(query: String): String = {
    print(query)
    val result = "this is dummy result"
    result
  }

  def showDBData(): Unit = {
    println(s"*** db : $db")
    // https://blog.csdn.net/u012234115/article/details/78658625
    val result = Await.result(db.run(sql"""select * from coffees""".as[(String, String, String, String)]), Duration.Inf)
  }
}
