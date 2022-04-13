package com.yen.Caliban4

import caliban.client.FieldBuilder._
import caliban.client._

object Client {

  type person
  object person {

    final case class personView[FriendsSelection](
        id: String,
        firstName: Option[String],
        lastName: Option[String],
        fullName: Option[String],
        email: Option[String],
        friends: Option[List[Option[FriendsSelection]]]
    )

    type ViewSelection[FriendsSelection] =
      SelectionBuilder[person, personView[FriendsSelection]]

    def view[FriendsSelection](
        friendsSelection: SelectionBuilder[person, FriendsSelection]
    ): ViewSelection[FriendsSelection] =
      (id ~ firstName ~ lastName ~ fullName ~ email ~ friends(friendsSelection))
        .map { case (id, firstName, lastName, fullName, email, friends) =>
          personView(id, firstName, lastName, fullName, email, friends)
        }

    def id: SelectionBuilder[person, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
    def firstName: SelectionBuilder[person, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("firstName", OptionOf(Scalar()))
    def lastName: SelectionBuilder[person, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("lastName", OptionOf(Scalar()))
    def fullName: SelectionBuilder[person, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("fullName", OptionOf(Scalar()))
    def email: SelectionBuilder[person, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("email", OptionOf(Scalar()))
    def friends[A](
        innerSelection: SelectionBuilder[person, A]
    ): SelectionBuilder[person, Option[List[Option[A]]]] =
      _root_.caliban.client.SelectionBuilder
        .Field("friends", OptionOf(ListOf(OptionOf(Obj(innerSelection)))))
  }

  type Query = _root_.caliban.client.Operations.RootQuery
  object Query {
    def allPeople[A](
        innerSelection: SelectionBuilder[person, A]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, List[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("allPeople", ListOf(Obj(innerSelection)))
  }

}

