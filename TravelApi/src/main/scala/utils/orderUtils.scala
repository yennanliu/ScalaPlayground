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

  def showOrderNum(): Int = {
    println("Total order count : " + orderNum)
    orderNum
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

  // TODO : fix this, make its as default/automatic process
  def initOrder(): Unit = {
    // init orders
    //    val o1 = new orderUtils("u0001", "c0001")
    //    val o2 = new orderUtils("u0002", "c0002")
    //    val o3 = new orderUtils("u0003", "c0003")
    //
    //    orderUtils.makeOrder(o1)
    //    orderUtils.makeOrder(o2)
    //    orderUtils.makeOrder(o3)
  }
}
