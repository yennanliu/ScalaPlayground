package com.yen.customerCRM.service

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._
import com.yen.customerCRM.bean.Customer

class CustomerService {
  // init
  val c1 = new Customer(1, "jim", 'm', 29, "12345", "jim@google.com")
  val c2 = new Customer(2, "Lynn", 'f', 17, "98745", "lynn@fb.com")
  val customers = ArrayBuffer[Customer](c1, c2)

  // attr
  // total customer amount
  var customerNum = 2

  // method

  // init customer db
  def init():Unit = {
    // init
    val c1 = new Customer(3, "Price", 'm', 77, "00", "price@aws.com")
    val c2 = new Customer(4, "lisa", 'f', 32, "454", "lisa@ms.com")

    val newCustomers = ArrayBuffer[Customer](c1, c2)
    for (customer <- newCustomers){
      add(customer)
      this.customerNum += 1
    }
  }

  // get customer list
  def list(): ArrayBuffer[Customer] = {
    this.customers
  }

  //return customer with id
  def getCustomer(id:Int):Any = {
      val index = findIndex(id)
      index match{
        case _ if index >= 0 && index < customers.length => customers(index)
        case _ if index < 0 || index > customers.length => "invalid user id"
      }
  }

  // add new customer
  def add(customer: Customer): Boolean = {
    customerNum += 1
    try {
      customers.append(customer)
      true
    } catch {
      case _ => {
        println("adding customer failed")
        false
      }
    }
  }

  // method that find customer index
  def findIndex(id: Int): Int = {
    var index = -1
    breakable {
      for (i <- 0 until customers.length){
        if (customers(i).id == id){
          index = i
          break()
          }
        }
      }
      index
    }

  // method that modify customer
  def modify(id:Int, customer:Customer):Boolean = {
    val index = findIndex(id)
    if (index != -1 || index < customers.length){
      customers(index) = customer
      true
    } else {
      false
    }
  }

  // method that delete customer
  def delete(id:Int):Boolean = {
    val index = findIndex(id)
    if (index > customers.length || index < 0){
      println("plz insert valid id, delete customer failed")
      false
    }
    try{
      customers.remove(index)
      true
    } catch {
      case _ => {
        println("delete customer failed")
        false
      }
    }
  }
}
