package schema

import slick.driver.MySQLDriver.api._
// https://blog.csdn.net/u012234115/article/details/78658625

object UserSchema {
  // table name: scala_model
  case class UserInfo(id: Long, name: String, age: Int)

  class SlickModelTable(tag: Tag) extends Table[UserInfo](tag, "scala_model")
  {
    // define column attribute
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc) // make sure here is primary key and auto inc(return column needed)
    def name = column[String]("name")
    def age = column[Int]("age")
    def * = (id, name, age) <> (UserInfo.tupled, UserInfo.unapply)
  }
  def slick_table = TableQuery[SlickModelTable]
}
