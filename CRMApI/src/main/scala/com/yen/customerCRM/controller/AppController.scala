package com.yen.customerCRM.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.{Request, Response}

import com.yen.customerCRM.service.CustomerService

object AppController {

  val customerService = new CustomerService

  class getUsers extends Controller {
    get("/api/v1/users") {
      requests: Request =>
        customerService.list()
    }
  }
}
