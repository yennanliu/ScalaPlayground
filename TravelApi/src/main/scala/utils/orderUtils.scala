package utils

import scala.collection.mutable.ListBuffer

// https://github.com/yennanliu/utility_Scala/blob/master/src/main/scala/ScalaBasic/CompanionDemo5.scala

// let's make the orderUtil via "companion" approach

class orderUtil(UName: String, oID: String){
  var userName:String = UName
  var orderID:String = oID
}

object orderUtil{

  var orderNum: Int = 0

  var orders: ListBuffer[String] = new ListBuffer[String]()

  def makeOrder(o: orderUtil): Unit = {
    println(o.userName + " make order !")
    orderNum += 1
    orders += o.orderID
  }

  def showOrderNum(): Unit = {
    println("Total order count : " + orderNum)
  }

  def showAllOrders(): Unit = {
    println("All orders : " + orders)
  }

}

// test
//object test extends App {
//
//  // define orders
//  val o1 = new orderUtil("tim", "C-0001")
//  val o2 = new orderUtil("jack", "C-0002")
//  val o3 = new orderUtil("vivi", "C-0003")
//
//  // make orders!
//  orderUtil.makeOrder(o1)
//  orderUtil.makeOrder(o2)
//  orderUtil.makeOrder(o3)
//
//  orderUtil.orderNum
//  orderUtil.showAllOrders
//}


//import base.orderUtilsBase
//
//class orderUtil extends orderUtilsBase {
//  override def checkOrder(orderId:String): Unit = {
//    println(s"this is order detail :  , orderId = $orderId")
//  }
//
//  override def makeOrder(userId: String): Unit = {
//    println(s"user $userId make order!")
//  }
//}
