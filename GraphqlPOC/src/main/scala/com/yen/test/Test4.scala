package com.yen.test

// https://sangria-graphql.github.io/learn/

import sangria.macros._

import scala.text.Document
import sangria.renderer.QueryRenderer
import sangria.macros._
import sangria.ast
import sangria.execution.Executor
import sangria.macros.derive.{AddFields, DocumentField, ExcludeFields, ObjectTypeDescription, ObjectTypeName, RenameField}
import sangria.schema._

object Test4 extends App {

  // parse query
  val parsed: ast.Value =
    graphqlInput"""
    {
      id: "1234345"
      version: 2 # changed 2 times
      deliveries: [
        {id: 123, received: false, note: null, state: OPEN}
      ]
    }
  """

  println(parsed.renderPretty)

  // define schema


  val ast =
    graphql"""
    schema {
      query: Hello
    }

    type Hello {
      bar: Bar
    }

    type Bar {
      isColor: Boolean
    }
  """

  val clientSchema: Schema[Any, Any] =
    Schema.buildFromAst(ast)

  val extensions =
    gql"""
    extend type Article {
      comments: [Comment]! @loadComments
    }

    type Comment {
      text: String!
      author: CommentAuthor!
    }

    type CommentAuthor {
      name: String!
      lastComment: Comment
    }
  """

  val ArticleType = deriveObjectType[Repo, Article]()

  val IdArg = Argument("id", StringType)

  val QueryType = ObjectType("Query", fields[Repo, Unit](
    Field("article", OptionType(ArticleType),
      arguments = IdArg :: Nil,
      resolve = c => c.ctx.loadArticle(c arg IdArg))))

  val staticSchema = Schema(QueryType)

  val schema = staticSchema.extend(extensions, builder)

  Executor.execute(schema, query, root = initialData)

  Executor.execute(query)

}
