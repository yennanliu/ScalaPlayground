package services

import schema.CoffeeSchema.{Coffees, Suppliers}
import slick.driver.SQLiteDriver.api._

object DBRunner {

  //TOFIX
  val tablename = "tablename"
  val db = Database.forConfig(tablename)
  val setup = DBIO.seq()
}
