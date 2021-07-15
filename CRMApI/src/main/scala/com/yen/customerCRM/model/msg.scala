package com.yen.customerCRM.model

import com.yen.customerCRM.bean.Customer

class msg {

}

case class HiRequest(id: Long, name: String)

case class userUpdateRequest(customer: Customer)

case class customerInfo(id:Int,
                        name:String,
                        gender:Char,
                        age:Short,
                        tel:String,
                        email:String)
