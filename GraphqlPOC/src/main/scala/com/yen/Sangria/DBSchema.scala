//package com.yen.sangriaTest
//
//import com.sun.javadoc.Tag
//import com.sun.tools.javac.util.Name.Table
//import com.yen.sangriaTest.models._
//
//object DBSchema {
//  //In the object body:
//
//  //1
//  class LinksTable(tag: Tag) extends Table[Link](tag, "LINKS"){
//
//    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
//    def url = column[String]("URL")
//    def description = column[String]("DESCRIPTION")
//
//    def * = (id, url, description).mapTo[Link]
//
//  }
//
//  //2
//  val Links = TableQuery[LinksTable]
//
//  //3
//  val databaseSetup = DBIO.seq(
//    Links.schema.create,
//
//    Links forceInsertAll Seq(
//      Link(1, "http://howtographql.com", "Awesome community driven GraphQL tutorial"),
//      Link(2, "http://graphql.org", "Official GraphQL web page"),
//      Link(3, "https://graphql.org/", "GraphQL specification")
//    )
//  )
//}
