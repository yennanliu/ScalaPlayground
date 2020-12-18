//package dev
//
//import org.slf4j.LoggerFactory
//import slick.driver.MySQLDriver.api._
//import scala.concurrent.ExecutionContext.Implicits.global // support andThen
//import scala.concurrent.Await
//import scala.concurrent.duration._
//import scala.util.{Failure, Success}
//
//// UDF
//import schema.UserSchema.slick_table
//
//// https://blog.csdn.net/u012234115/article/details/78658625
//
//object UserDBTestRunner extends App{
//  // init logger
//  val logger = LoggerFactory.getLogger(getClass.getSimpleName)
//  logger.info("slick test end")
//
//  // config database
//  val db = Database.forURL(
//    url = "jdbc:mysql://localhost:3306/db_example?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
//    driver = "com.mysql.jdbc.Driver",
//    user = "springuser",
//    password = "ThePassword")
//
//  // ---- use function
//
//  // query all
//  // slick run returns a future, we can use andThen to get async response and use Await.result to get result
//  // usage1
//  val query_action = slick_table.result
//  val res1 = db.run(query_action).andThen {
//    case Success(_) => println("query success")
//    case Failure(e) => println("query failed ", e.getMessage)
//  }
//
//  // usage2
//  db.run(slick_table.result).map {
//    result => println(result)
//  }
//
//  // block thread to get select result
//  Await.result(res1, 10 seconds) // specify the timeout
//
//  // query by condition
//  val res2 = Await.result(db.run(slick_table.filter(_.age > 25).result), Duration.Inf)
//
//  // add（only 1 record)
//  // val user1 = UserInfo(6L, "scarllet", 19)
//  // val res3 = Await.result(db.run(slick_table += user1), Duration.Inf) // return the insert numbers: 1, so no need to return
//
//  case class UserInfo(id: Long, name: String, age: Int)
//
//  // add(batch records)
//  val user1 = UserInfo(6L, "scarllet", 19)
//  val user2 = UserInfo(7L, "mary", 21)
//  val newArray = Seq[UserInfo](user1, user2)
//  val res3 = Await.result(db.run(slick_table ++= newArray), Duration.Inf) // return the insert numbers: 2, so no need to return
//
//  // update
//  val new_user = UserInfo(3L, "tashaxing", 23)
//  val res4 = Await.result(db.run(slick_table.filter(_.id === new_user.id).update(new_user)), Duration.Inf) // return effected row numbers
//
//  // delete
//  val res5 = Await.result(db.run(slick_table.filter(_.name === "lucy").delete), Duration.Inf)
//
//  // return main column after insert
//  val user = UserInfo(0, "ethan", 21)
//  val save_sql = (slick_table returning slick_table.map(_.id)) += user
//  val user_id = Await.result(db.run(save_sql), Duration.Inf) // return created id
//
//  // ---- use sql
//
//  // query sql
//  val res6 = Await.result(db.run(sql"""select * from scala_model where name = 'mary'""".as[(Long, String, Int)]), Duration.Inf)
//
//  // insert sql
//  val id = 10L
//  val name = "wilson"
//  val age = 29
//  val res7 = Await.result(db.run(sqlu"""insert into scala_model values($id, $name, $age)"""), Duration.Inf) // use variable outside
//  // val res7 = Await.result(db.run(sqlu"""insert into scala_model values(10, 'bob', 28)"""), Duration.Inf) // use variable in string
//
//  // update sql
//  val res8 = Await.result(db.run(sqlu"""update scala_model set name='lily' where id=4"""), Duration.Inf)
//
//  // delete sql
//  val res9 = Await.result(db.run(sqlu"""delete from scala_model where name='mary'"""), Duration.Inf)
//
//  logger.debug("slick test end")
//}