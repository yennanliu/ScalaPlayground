package com.yen.TaxiService.controller

import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

import com.yen.TaxiService.model.bookRequest
import com.yen.TaxiService.service.bookService

/**
 *  taxi booking controller
 */

object bookController {
  val book_service = new bookService()

  // book endpoint
  class book extends Controller {
    post("/api/book"){
      request:bookRequest =>
        val source = request.source
        val destination = request.destination
        book_service.book(source, destination)
    }
  }

  // reset endpoint
  class resetStatus extends Controller {
    post("/api/reset"){
      require:Request =>
        book_service.reset()
    }
  }

  // tick endpoint
  class tickClock extends Controller {
    get("/api/tick"){
      require:Request =>
        book_service.tick()
    }
  }

  // listAll endpoint
  class listAllCar extends Controller {
    get("/api/all"){
      require:Request =>
        book_service.listAll()
    }
  }

}
