package com.yen.Caliban5

import caliban.client.CalibanClientError.DecodingError
import caliban.client.FieldBuilder._
import caliban.client._
import caliban.client.__Value._

object Client {

  sealed trait Episode extends scala.Product with scala.Serializable {
    def value: String
  }
  object Episode {
    case object NEWHOPE extends Episode { val value: String = "NEWHOPE" }
    case object EMPIRE extends Episode { val value: String = "EMPIRE" }
    case object JEDI extends Episode { val value: String = "JEDI" }

    implicit val decoder: ScalarDecoder[Episode] = {
      case __StringValue("NEWHOPE") => Right(Episode.NEWHOPE)
      case __StringValue("EMPIRE")  => Right(Episode.EMPIRE)
      case __StringValue("JEDI")    => Right(Episode.JEDI)
      case other =>
        Left(DecodingError(s"Can't build Episode from input $other"))
    }
    implicit val encoder: ArgEncoder[Episode] = {
      case Episode.NEWHOPE => __EnumValue("NEWHOPE")
      case Episode.EMPIRE  => __EnumValue("EMPIRE")
      case Episode.JEDI    => __EnumValue("JEDI")
    }

    val values: scala.collection.immutable.Vector[Episode] =
      scala.collection.immutable.Vector(NEWHOPE, EMPIRE, JEDI)
  }

  type Character
  object Character {

    final case class CharacterView[FriendsSelection](
        id: String,
        name: Option[String],
        friends: List[FriendsSelection],
        appearsIn: Option[List[Option[Episode]]]
    )

    type ViewSelection[FriendsSelection] =
      SelectionBuilder[Character, CharacterView[FriendsSelection]]

    def view[FriendsSelection](
        friendsSelectionOnDroid: SelectionBuilder[Droid, FriendsSelection],
        friendsSelectionOnHuman: SelectionBuilder[Human, FriendsSelection]
    ): ViewSelection[FriendsSelection] = (id ~ name ~ friends(
      friendsSelectionOnDroid,
      friendsSelectionOnHuman
    ) ~ appearsIn).map { case (id, name, friends, appearsIn) =>
      CharacterView(id, name, friends, appearsIn)
    }

    /** The id of the character.
      */
    def id: SelectionBuilder[Character, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())

    /** The name of the character.
      */
    def name: SelectionBuilder[Character, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))

    /** The friends of the character, or an empty list if they have none.
      */
    def friends[A](
        onDroid: SelectionBuilder[Droid, A],
        onHuman: SelectionBuilder[Human, A]
    ): SelectionBuilder[Character, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "friends",
        ListOf(ChoiceOf(Map("Droid" -> Obj(onDroid), "Human" -> Obj(onHuman))))
      )

    /** Which movies they appear in.
      */
    def appearsIn: SelectionBuilder[Character, Option[List[Option[Episode]]]] =
      _root_.caliban.client.SelectionBuilder
        .Field("appearsIn", OptionOf(ListOf(OptionOf(Scalar()))))

    /** The friends of the character, or an empty list if they have none.
      */
    def friendsOption[A](
        onDroid: Option[SelectionBuilder[Droid, A]] = None,
        onHuman: Option[SelectionBuilder[Human, A]] = None
    ): SelectionBuilder[Character, List[Option[A]]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "friends",
        ListOf(
          ChoiceOf(
            Map(
              "Droid" -> onDroid.fold[FieldBuilder[Option[A]]](NullField)(a =>
                OptionOf(Obj(a))
              ),
              "Human" -> onHuman.fold[FieldBuilder[Option[A]]](NullField)(a =>
                OptionOf(Obj(a))
              )
            )
          )
        )
      )

    /** The friends of the character, or an empty list if they have none.
      */
    def friendsInterface[A](
        friends: SelectionBuilder[Character, A]
    ): SelectionBuilder[Character, List[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("friends", ListOf(Obj(friends)))
  }

  type Droid
  object Droid {

    final case class DroidView[FriendsSelection](
        id: String,
        name: Option[String],
        friends: List[FriendsSelection],
        appearsIn: Option[List[Option[Episode]]],
        primaryFunction: Option[String]
    )

    type ViewSelection[FriendsSelection] =
      SelectionBuilder[Droid, DroidView[FriendsSelection]]

    def view[FriendsSelection](
        friendsSelectionOnDroid: SelectionBuilder[Droid, FriendsSelection],
        friendsSelectionOnHuman: SelectionBuilder[Human, FriendsSelection]
    ): ViewSelection[FriendsSelection] = (id ~ name ~ friends(
      friendsSelectionOnDroid,
      friendsSelectionOnHuman
    ) ~ appearsIn ~ primaryFunction).map {
      case (id, name, friends, appearsIn, primaryFunction) =>
        DroidView(id, name, friends, appearsIn, primaryFunction)
    }

    /** The id of the droid.
      */
    def id: SelectionBuilder[Droid, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())

    /** The name of the droid.
      */
    def name: SelectionBuilder[Droid, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))

    /** The friends of the droid, or an empty list if they have none.
      */
    def friends[A](
        onDroid: SelectionBuilder[Droid, A],
        onHuman: SelectionBuilder[Human, A]
    ): SelectionBuilder[Droid, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "friends",
        ListOf(ChoiceOf(Map("Droid" -> Obj(onDroid), "Human" -> Obj(onHuman))))
      )

    /** Which movies they appear in.
      */
    def appearsIn: SelectionBuilder[Droid, Option[List[Option[Episode]]]] =
      _root_.caliban.client.SelectionBuilder
        .Field("appearsIn", OptionOf(ListOf(OptionOf(Scalar()))))

    /** The primary function of the droid.
      */
    def primaryFunction: SelectionBuilder[Droid, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("primaryFunction", OptionOf(Scalar()))

    /** The friends of the droid, or an empty list if they have none.
      */
    def friendsOption[A](
        onDroid: Option[SelectionBuilder[Droid, A]] = None,
        onHuman: Option[SelectionBuilder[Human, A]] = None
    ): SelectionBuilder[Droid, List[Option[A]]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "friends",
        ListOf(
          ChoiceOf(
            Map(
              "Droid" -> onDroid.fold[FieldBuilder[Option[A]]](NullField)(a =>
                OptionOf(Obj(a))
              ),
              "Human" -> onHuman.fold[FieldBuilder[Option[A]]](NullField)(a =>
                OptionOf(Obj(a))
              )
            )
          )
        )
      )

    /** The friends of the droid, or an empty list if they have none.
      */
    def friendsInterface[A](
        friends: SelectionBuilder[Character, A]
    ): SelectionBuilder[Droid, List[A]] = _root_.caliban.client.SelectionBuilder
      .Field("friends", ListOf(Obj(friends)))
  }

  type Human
  object Human {

    final case class HumanView[FriendsSelection](
        id: String,
        name: Option[String],
        friends: List[FriendsSelection],
        appearsIn: Option[List[Option[Episode]]],
        homePlanet: Option[String]
    )

    type ViewSelection[FriendsSelection] =
      SelectionBuilder[Human, HumanView[FriendsSelection]]

    def view[FriendsSelection](
        friendsSelectionOnDroid: SelectionBuilder[Droid, FriendsSelection],
        friendsSelectionOnHuman: SelectionBuilder[Human, FriendsSelection]
    ): ViewSelection[FriendsSelection] = (id ~ name ~ friends(
      friendsSelectionOnDroid,
      friendsSelectionOnHuman
    ) ~ appearsIn ~ homePlanet).map {
      case (id, name, friends, appearsIn, homePlanet) =>
        HumanView(id, name, friends, appearsIn, homePlanet)
    }

    /** The id of the human.
      */
    def id: SelectionBuilder[Human, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())

    /** The name of the human.
      */
    def name: SelectionBuilder[Human, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("name", OptionOf(Scalar()))

    /** The friends of the human, or an empty list if they have none.
      */
    def friends[A](
        onDroid: SelectionBuilder[Droid, A],
        onHuman: SelectionBuilder[Human, A]
    ): SelectionBuilder[Human, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "friends",
        ListOf(ChoiceOf(Map("Droid" -> Obj(onDroid), "Human" -> Obj(onHuman))))
      )

    /** Which movies they appear in.
      */
    def appearsIn: SelectionBuilder[Human, Option[List[Option[Episode]]]] =
      _root_.caliban.client.SelectionBuilder
        .Field("appearsIn", OptionOf(ListOf(OptionOf(Scalar()))))

    /** The home planet of the human, or null if unknown.
      */
    def homePlanet: SelectionBuilder[Human, Option[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("homePlanet", OptionOf(Scalar()))

    /** The friends of the human, or an empty list if they have none.
      */
    def friendsOption[A](
        onDroid: Option[SelectionBuilder[Droid, A]] = None,
        onHuman: Option[SelectionBuilder[Human, A]] = None
    ): SelectionBuilder[Human, List[Option[A]]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "friends",
        ListOf(
          ChoiceOf(
            Map(
              "Droid" -> onDroid.fold[FieldBuilder[Option[A]]](NullField)(a =>
                OptionOf(Obj(a))
              ),
              "Human" -> onHuman.fold[FieldBuilder[Option[A]]](NullField)(a =>
                OptionOf(Obj(a))
              )
            )
          )
        )
      )

    /** The friends of the human, or an empty list if they have none.
      */
    def friendsInterface[A](
        friends: SelectionBuilder[Character, A]
    ): SelectionBuilder[Human, List[A]] = _root_.caliban.client.SelectionBuilder
      .Field("friends", ListOf(Obj(friends)))
  }

  type Query = _root_.caliban.client.Operations.RootQuery
  object Query {
    @deprecated("Use `human` or `droid` fields instead", "")
    def hero[A](
        episode: Option[Episode] = None
    )(onDroid: SelectionBuilder[Droid, A], onHuman: SelectionBuilder[Human, A])(
        implicit encoder0: ArgEncoder[Option[Episode]]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder.Field(
        "hero",
        ChoiceOf(Map("Droid" -> Obj(onDroid), "Human" -> Obj(onHuman))),
        arguments = List(Argument("episode", episode, "Episode")(encoder0))
      )
    @deprecated("Use `human` or `droid` fields instead", "")
    def heroOption[A](episode: Option[Episode] = None)(
        onDroid: Option[SelectionBuilder[Droid, A]] = None,
        onHuman: Option[SelectionBuilder[Human, A]] = None
    )(implicit
        encoder0: ArgEncoder[Option[Episode]]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "hero",
        ChoiceOf(
          Map(
            "Droid" -> onDroid.fold[FieldBuilder[Option[A]]](NullField)(a =>
              OptionOf(Obj(a))
            ),
            "Human" -> onHuman.fold[FieldBuilder[Option[A]]](NullField)(a =>
              OptionOf(Obj(a))
            )
          )
        ),
        arguments = List(Argument("episode", episode, "Episode")(encoder0))
      )
    def human[A](id: String)(innerSelection: SelectionBuilder[Human, A])(
        implicit encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, Option[A]] =
      _root_.caliban.client.SelectionBuilder.Field(
        "human",
        OptionOf(Obj(innerSelection)),
        arguments = List(Argument("id", id, "String!")(encoder0))
      )
    def droid[A](id: String)(innerSelection: SelectionBuilder[Droid, A])(
        implicit encoder0: ArgEncoder[String]
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder.Field(
        "droid",
        Obj(innerSelection),
        arguments = List(Argument("id", id, "String!")(encoder0))
      )
  }

}

