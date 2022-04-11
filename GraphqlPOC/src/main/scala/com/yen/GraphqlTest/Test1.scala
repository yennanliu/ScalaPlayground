//package com.yen.GraphqlTest
//
//// https://www.graphql-java.com/tutorials/getting-started-with-spring-boot
//// https://www.jianshu.com/p/4ede220b713e
//
//import graphql.GraphQL
//import graphql.schema.GraphQLSchema
//import graphql.schema.StaticDataFetcher
//import graphql.schema.idl.{RuntimeWiring, SchemaParser, TypeDefinitionRegistry, TypeRuntimeWiring}
//
//object Test1 extends App {
//  // 1\. 定义Schema, 一般会定义在一个schema文件中
//  val schema: String = "type Query{hello: String}"
//  // 2\. 解析Schema
//  val schemaParser: SchemaParser = new SchemaParser
//  val typeDefinitionRegistry: TypeDefinitionRegistry = schemaParser.parse (schema)
//  // 为Schema 中hello方法绑定获取数据的方法
//  val runtimeWiring: RuntimeWiring = RuntimeWiring.newRuntimeWiring.build() // 这里绑定的是最简单的静态数据数据获取器, 正常使用时,获取数据的方法返回一个DataFetcher实现即可
//  ("Query", (builder: TypeRuntimeWiring.Builder) => builder.dataFetcher ("hello", new StaticDataFetcher ("world") ) )
//  // 将TypeDefinitionRegistry与RuntimeWiring结合起来生成GraphQLSchema
//  val schemaGenerator = schemaGenerator("wef")
//  val graphQLSchema: GraphQLSchema = ??? //schemaGenerator.makeExecutableSchema (typeDefinitionRegistry, runtimeWiring)
//  // 实例化GraphQL, GraphQL为执行GraphQL语言的入口
//  val graphQL = GraphQL.newGraphQL (graphQLSchema).build()
//  // 执行查询
//  val executionResult = graphQL.execute ("{hello}")
//  // 打印执行结果
//  println (executionResult.getData.toString)
//}
