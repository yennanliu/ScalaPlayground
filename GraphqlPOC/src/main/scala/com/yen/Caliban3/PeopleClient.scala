//package com.yen.Caliban3
//
//import caliban.client.CalibanClientError.DecodingError
//import caliban.client.FieldBuilder._
//import caliban.client._
//import caliban.client.__Value._
//
//object PeopleClient {
//
//  sealed trait Origin extends scala.Product with scala.Serializable
//  object Origin {
//    case object BELT  extends Origin
//    case object EARTH extends Origin
//    case object MARS  extends Origin
//
//    implicit val decoder: ScalarDecoder[Origin] = {
//      case __StringValue("BELT")  => Right(Origin.BELT)
//      case __StringValue("EARTH") => Right(Origin.EARTH)
//      case __StringValue("MARS")  => Right(Origin.MARS)
//      case other                  => Left(DecodingError(s"Can't build Origin from input $other"))
//    }
//    implicit val encoder: ArgEncoder[Origin]    = {
//      case Origin.BELT  => __EnumValue("BELT")
//      case Origin.EARTH => __EnumValue("EARTH")
//      case Origin.MARS  => __EnumValue("MARS")
//    }
//  }
//
//  // TODO : fix below
//  type People
//  object People {
//    def firstName: SelectionBuilder[People, String]    = _root_.caliban.client.SelectionBuilder.Field("firstName", Scalar())
//    def lastName: SelectionBuilder[People, String]    = _root_.caliban.client.SelectionBuilder.Field("lastName", Scalar())
//    def id: SelectionBuilder[People, String]    = _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
//  }
//
//  type allPeople
//  object allPeople {
//    def `type`: SelectionBuilder[allPeople, String] =
//      _root_.caliban.client.SelectionBuilder.Field("type", Scalar())
//    def firstName: SelectionBuilder[allPeople, String] =
//      _root_.caliban.client.SelectionBuilder.Field("firstName", Scalar())
//    def lastName: SelectionBuilder[allPeople, String] =
//      _root_.caliban.client.SelectionBuilder.Field("lastName", Scalar())
//    def id: SelectionBuilder[allPeople, String] =
//      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
//    def friends: SelectionBuilder[allPeople, List[People]] =
//      _root_.caliban.client.SelectionBuilder.Field("friends", ListOf(Scalar()))
//  }
//
//}
