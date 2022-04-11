package com.yen.calibanTest

import caliban.client.SelectionBuilder
import com.sun.management.VMOption.Origin

object Test2 extends App {

  object Character {
    def name: SelectionBuilder[Character, String]            = null
    def nicknames: SelectionBuilder[Character, List[String]] = null
    def origin: SelectionBuilder[Character, Origin]          = null
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
