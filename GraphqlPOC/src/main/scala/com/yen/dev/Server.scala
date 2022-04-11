//package com.yen.test
//
//// https://sangria-graphql.github.io/getting-started/
//
//import akka.actor.ActorSystem
//import akka.actor.FSM.Failure
//import akka.actor.Status.Success
//import akka.stream.ActorMaterializer
//import sangria.execution.{ErrorWithResolver, Executor, QueryAnalysisError}
//import sangria.parser.{Document, QueryParser}
//import spray.json.{JsObject, JsString, JsValue}
//import sangria.marshalling.sprayJson._
//
//object Server extends App {
//  implicit val system = ActorSystem("sangria-server")
//  implicit val materializer = ActorMaterializer()
//
//  import system.dispatcher
//
//  val route: Route =
//    (post & path("graphql")) {
//      entity(as[JsValue]) { requestJson =>
//        graphQLEndpoint(requestJson)
//      }
//    } ~
//      get {
//        getFromResource("graphiql.html")
//      }
//
//  Http().bindAndHandle(route, "0.0.0.0", 8080)
//
//
//  def graphQLEndpoint(requestJson: JsValue) = {
//    val JsObject(fields) = requestJson
//
//    val JsString(query) = fields("query")
//
//    val operation = fields.get("operationName") collect {
//      case JsString(op) => op
//    }
//
//    val vars = fields.get("variables") match {
//      case Some(obj: JsObject) => obj
//      case _ => JsObject.empty
//    }
//
//    QueryParser.parse(query) match {
//
//      // query parsed successfully, time to execute it!
//      case Success(queryAst) =>
//        complete(executeGraphQLQuery(queryAst, operation, vars))
//
//      // can't parse GraphQL query, return error
//      case Failure(error) =>
//        complete(BadRequest, JsObject("error" -> JsString(error.getMessage)))
//    }
//
//  }
//
//  def executeGraphQLQuery(query: Document, op: Option[String], vars: JsObject) =
//    Executor.execute(schema, query, new ProductRepo, variables = vars, operationName = op)
//      .map(OK -> _)
//      .recover {
//        case error: QueryAnalysisError => BadRequest -> error.resolveError
//        case error: ErrorWithResolver => InternalServerError -> error.resolveError
//      }
//}