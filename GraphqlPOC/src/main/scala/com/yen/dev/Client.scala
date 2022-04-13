package com.yen.dev

import caliban.client.FieldBuilder._
import caliban.client._

object Client {

  type Book
  object Book {

    final case class BookView[AuthorSelection](
        id: String,
        name: Option[String],
        pageCount: Option[Int],
        author: Option[AuthorSelection]
    )

    type ViewSelection[AuthorSelection] =
      SelectionBuilder[Book, BookView[AuthorSelection]]

    def view[AuthorSelection](
        authorSelection: SelectionBuilder[Author, AuthorSelection]
    ): ViewSelection[AuthorSelection] =
      (id ~ name ~ pageCount ~ author(authorSelection)).map {
        case (id, name, pageCount, author) =>
          BookView(id, name, pageCount, author)
      }

    def id: SelectionBuilder[Book, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
    def name: SelectionBuilder[Book, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))
    def pageCount: SelectionBuilder[Book, Option[Int]] =
      _root_.caliban.client.SelectionBuilder
        .Field("pageCount", OptionOf(Scalar()))
    def author[A](
        innerSelection: SelectionBuilder[Author, A]
    ): SelectionBuilder[Book, Option[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("author", OptionOf(Obj(innerSelection)))
  }

  type Author
  object Author {

    final case class AuthorView(
        id: String,
        firstName: Option[String],
        lastName: Option[String]
    )

    type ViewSelection = SelectionBuilder[Author, AuthorView]

    def view: ViewSelection = (id ~ firstName ~ lastName).map {
      case (id, firstName, lastName) => AuthorView(id, firstName, lastName)
    }

    def id: SelectionBuilder[Author, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
    def firstName: SelectionBuilder[Author, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("firstName", OptionOf(Scalar()))
    def lastName: SelectionBuilder[Author, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("lastName", OptionOf(Scalar()))
  }

}

