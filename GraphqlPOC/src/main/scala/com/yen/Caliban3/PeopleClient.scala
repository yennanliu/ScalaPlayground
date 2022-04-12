package com.yen.Caliban3

import caliban.client.CalibanClientError.DecodingError
import caliban.client.FieldBuilder._
import caliban.client._
import caliban.client.__Value._

object PeopleClient {

  sealed trait Origin extends scala.Product with scala.Serializable
  object Origin {
    case object BELT  extends Origin
    case object EARTH extends Origin
    case object MARS  extends Origin

    implicit val decoder: ScalarDecoder[Origin] = {
      case __StringValue("BELT")  => Right(Origin.BELT)
      case __StringValue("EARTH") => Right(Origin.EARTH)
      case __StringValue("MARS")  => Right(Origin.MARS)
      case other                  => Left(DecodingError(s"Can't build Origin from input $other"))
    }
    implicit val encoder: ArgEncoder[Origin]    = {
      case Origin.BELT  => __EnumValue("BELT")
      case Origin.EARTH => __EnumValue("EARTH")
      case Origin.MARS  => __EnumValue("MARS")
    }
  }

  // TODO : fix below
  type PeopleAttr
  object PeopleAttr {
    def firstName: SelectionBuilder[PeopleAttr, String]    = _root_.caliban.client.SelectionBuilder.Field("firstName", Scalar())
    def lastName: SelectionBuilder[PeopleAttr, String]    = _root_.caliban.client.SelectionBuilder.Field("lastName", Scalar())
    def id: SelectionBuilder[PeopleAttr, String]    = _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
  }

  type allPeople
  object allPeople {
    def `type`: SelectionBuilder[allPeople, String] =
      _root_.caliban.client.SelectionBuilder.Field("type", Scalar())
    def firstName: SelectionBuilder[allPeople, String] =
      _root_.caliban.client.SelectionBuilder.Field("firstName", Scalar())
    def lastName: SelectionBuilder[allPeople, String] =
      _root_.caliban.client.SelectionBuilder.Field("lastName", Scalar())
    def id: SelectionBuilder[allPeople, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
    def friends: SelectionBuilder[allPeople, List[String]] =
      _root_.caliban.client.SelectionBuilder.Field("friends", ListOf(Scalar()))
  }

  type PeopleLocation
  object OperationLocation {
    def id: SelectionBuilder[PeopleLocation, Option[String]] =
      _root_.caliban.client.SelectionBuilder.Field("id", OptionOf(Scalar()))
  }

  type Searchable
  object Searchable {
    def allPeople[A](innerSelection: SelectionBuilder[PeopleAttr, A]): SelectionBuilder[Searchable, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("stations", ListOf(Obj(innerSelection)))
    def operationLocations[A](
                               innerSelection: SelectionBuilder[PeopleLocation, A]
                             ): SelectionBuilder[Searchable, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("operationLocations", ListOf(Obj(innerSelection)))
    def peopleLocations[A](
                               innerSelection: SelectionBuilder[PeopleLocation, A]
                             ): SelectionBuilder[Searchable, List[A]] =
      _root_.caliban.client.SelectionBuilder.Field("operationLocations", ListOf(Obj(innerSelection)))
  }

  // Query
  type Query = _root_.caliban.client.Operations.RootQuery
  object Query {
    def search[A](searchTerm: Option[String] = None)(
      innerSelection: SelectionBuilder[Searchable, A]
    )(implicit encoder0: ArgEncoder[Option[String]]): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, A] =
      _root_.caliban.client.SelectionBuilder
        .Field("search", Obj(innerSelection), arguments = List(Argument("searchTerm", searchTerm, "String")(encoder0)))
  }
}
