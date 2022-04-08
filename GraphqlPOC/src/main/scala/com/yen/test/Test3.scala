package com.yen.test

// https://sangria-graphql.github.io/learn/

import java.util.concurrent.Executor

import sangria.ast.Document
import sangria.parser.QueryParser
import sangria.renderer.QueryRenderer

import scala.util.{Failure, Success}

object Test3 extends App {

//  val query =
//    """
//    query FetchLukeAndLeiaAliased(
//          $someVar: Int = 1.23
//          $anotherVar: Int = 123) @include(if: true) {
//      luke: human(id: "1000")@include(if: true){
//        friends(sort: NAME)
//      }
//
//      leia: human(id: "10103\n \u00F6 ö") {
//        name
//      }
//
//      ... on User {
//        birth{day}
//      }
//
//      ...Foo
//    }
//
//    fragment Foo on User @foo(bar: 1) {
//      baz
//    }
//  """

  val query =
    """
      |query AllPeopleWithFriends {
      |  allPeople {
      |    fullName
      |    id
      |    friends {
      |      firstName
      |      lastName
      |      email
      |      fullName
      |      id
      |    }
      |  }
      |}
      |""".stripMargin


  // Parse GraphQL query
  QueryParser.parse(query) match {
    case Success(document) =>
      // Pretty rendering of the GraphQL query as a `String`
      println("query format OK !!! >>>>>")
      println(document.renderPretty)

    case Failure(error) =>
      println(s"Syntax error: ${error.getMessage}")
  }


  //Executor.execute(schema, query, root = initialData)
}
