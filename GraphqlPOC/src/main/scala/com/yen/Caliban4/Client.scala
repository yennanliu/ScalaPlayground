package com.yen.Caliban4

import caliban.client.FieldBuilder._
import caliban.client._

object Client {

  type Person
  object Person {

    final case class PersonView[FriendsSelection](
        id: String,
        firstName: Option[String],
        lastName: Option[String],
        fullName: Option[String],
        email: Option[String],
        friends: Option[List[Option[FriendsSelection]]]
    )

    type ViewSelection[FriendsSelection] =
      SelectionBuilder[Person, PersonView[FriendsSelection]]

    def view[FriendsSelection](
        friendsSelection: SelectionBuilder[Person, FriendsSelection]
    ): ViewSelection[FriendsSelection] =
      (id ~ firstName ~ lastName ~ fullName ~ email ~ friends(friendsSelection))
        .map { case (id, firstName, lastName, fullName, email, friends) =>
          PersonView(id, firstName, lastName, fullName, email, friends)
        }

    def id: SelectionBuilder[Person, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
    def firstName: SelectionBuilder[Person, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("firstName", OptionOf(Scalar()))
    def lastName: SelectionBuilder[Person, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("lastName", OptionOf(Scalar()))
    def fullName: SelectionBuilder[Person, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("fullName", OptionOf(Scalar()))
    def email: SelectionBuilder[Person, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("email", OptionOf(Scalar()))
    def friends[A](
        innerSelection: SelectionBuilder[Person, A]
    ): SelectionBuilder[Person, Option[List[Option[A]]]] =
      _root_.caliban.client.SelectionBuilder
        .Field("friends", OptionOf(ListOf(OptionOf(Obj(innerSelection)))))
  }

  type Searchable
  object Searchable {

    def allPeople[A](
                      innerSelection: SelectionBuilder[Person, A]
                    ): SelectionBuilder[Searchable, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("allPeople", ListOf(Obj(innerSelection)))

    def friends[A](
                   innerSelection: SelectionBuilder[Person, A]
                 ): SelectionBuilder[Searchable, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("friends", ListOf(Obj(innerSelection)))

  }

  // Query
  type Query = _root_.caliban.client.Operations.RootQuery
  object Query {

    def PeopleWithId[A](id: String)(
      innerSelection: SelectionBuilder[Person, A]
    )(implicit encoder0: ArgEncoder[String]): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "person",
        OptionOf(Obj(innerSelection)),
        arguments = List(Argument("id", id, "String!")(encoder0))
      )

    def search[A](searchTerm: Option[String] = None)(
      innerSelection: SelectionBuilder[Searchable, A]
    )(implicit encoder0: ArgEncoder[Option[String]]): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder
        .Field("person", Obj(innerSelection), arguments = List(Argument("id", searchTerm, "String")(encoder0)))

    def search2[A](searchTerm: Option[String] = None)(
      innerSelection: SelectionBuilder[Searchable, A]
    )(implicit encoder0: ArgEncoder[Option[String]]): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder
        .Field("id", Obj(innerSelection), arguments = List(Argument("id", searchTerm, "String")(encoder0)))

    def get[A](searchTerm: Option[String] = None)(
      innerSelection: SelectionBuilder[Searchable, A]
    )(implicit encoder0: ArgEncoder[Option[String]]): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder
        .Field("allPeople", Obj(innerSelection))

  }


}

