package utils

import scala.collection.mutable.ListBuffer

// https://github.com/yennanliu/utility_Scala/blob/master/src/main/scala/ScalaBasic/CompanionDemo5.scala

// make the orderUtil Scala companion

class orderUtils(UName: String, oID: String){

  var userName:String = UName

  var orderID:String = oID
}

object orderUtils{

  var orderNum: Int = 0

  var orders: ListBuffer[String] = new ListBuffer[String]()

  def makeOrder(o: orderUtils): Unit = {
    println(o.userName + " make order !")
    orderNum += 1
    orders += o.orderID
  }

  def showOrderNum(): Unit = {
    println("Total order count : " + orderNum)
  }

  def showAllOrders() = {
    //println("All orders : " + orders)
    orders
  }

  def getOrder(o: orderUtils) = {
    if ( orders.contains(o.orderID) ) {
      //println("order id  = " + o.orderID)
      o.orderID
    } else {
      println("order not existed")
      showAllOrders()
    }
  }

}

// test
//object test extends App {
//
//  // define orders
//  val o1 = new orderUtils("tim", "C-0001")
//  val o2 = new orderUtils("jack", "C-0002")
//  val o3 = new orderUtils("vivi", "C-0003")
//
//  // make orders!
//  orderUtils.makeOrder(o1)
//  orderUtils.makeOrder(o2)
//  orderUtils.makeOrder(o3)
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
