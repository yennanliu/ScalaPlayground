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

}

