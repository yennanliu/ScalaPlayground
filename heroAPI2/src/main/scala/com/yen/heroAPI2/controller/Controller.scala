package com.yen.heroAPI2.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

import com.yen.heroAPI2.service.heroService

object Controller {

  val hero_service = new heroService()

  class getAllHero extends Controller {
    get("/api/v1/all_hero") { request: Request =>
      val heroes = hero_service.readAll()
      heroes.toString
    }
  }

  class hello extends Controller {
    get("/hi") { request: Request =>
      "hello !!!"
    }
  }
}
