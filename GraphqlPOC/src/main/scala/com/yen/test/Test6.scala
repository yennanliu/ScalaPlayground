package com.yen.test

import caliban.client.IntrospectionClient.Query
import caliban.client.Operations.RootQuery
import caliban.client.SelectionBuilder
import com.sun.management.VMOption.Origin


//import sttp.client._
//import sttp.client.asynch asynchttpclient.zio.AsyncHttpClientZioBackend

// https://ghostdogpr.github.io/caliban/docs/client.html#dependencies

object Test6 extends App {

  object Character {
    def name: SelectionBuilder[Character, String]            = ???
    def nicknames: SelectionBuilder[Character, List[String]] = ???
    def origin: SelectionBuilder[Character, Origin]          = ???
  }

  val selection: SelectionBuilder[Character, (String, List[String])] =
    Character.name ~ Character.nicknames

  case class CharacterView(name: String, nickname: List[String], origin: Origin)

  val character: SelectionBuilder[Character, CharacterView] =
    (Character.name ~ Character.nicknames ~ Character.origin)
      .mapN(CharacterView)

//
//  val query: SelectionBuilder[RootQuery, List[CharacterView]] =
//    Query.characters {
//      (Character.name ~ Character.nicknames ~ Character.origin)
//        .mapN(CharacterView)
//    }

//
//  val query: SelectionBuilder[RootQuery, List[CharacterView]] =
//    Query.characters {
//      (Character.name ~ Character.nicknames ~ Character.origin)
//        .mapN(CharacterView)
//    }

}
