# Graphql POC

## Quick start
```bash
# run the graphql server
cd zero-to-graphql/zero-scala
sbt run

# schema:
# https://github.com/steveluscher/zero-to-graphql/blob/master/zero-scala/src/main/scala/Repository.scala#L62

# make a graphql client request via CLI

# example 1
curl \
-X POST \
-H "Content-Type: application/json" \
--data '{ "query": "{ allPeople { id } } " }' \
http://localhost:8080/graphql

# example 2
curl \
-X POST \
-H "Content-Type: application/json" \
--data '{ "query": "{ allPeople { fullName id } } " }' \
http://localhost:8080/graphql

# example 3
# curl \
# -X POST \
# -H "Content-Type: application/json" \
# --data '{ "query": "{  person (id:1001){ id fullName } " }' \
# http://localhost:8080/graphql

# example 4 : get graphql schema
# https://medium.com/@mrthankyou/how-to-get-a-graphql-schema-28915025de0e#:~:text=How%20To%20Get%20The%20Schema%20%E2%80%94%20Introspection%20Queries,called%20a%20GraphQL%20introspection%20query.
curl 'http://localhost:8080/graphql' \
-H 'Content-Type: application/json' \
-H 'Accept: application/json' \
--compressed \
--data-binary '{"query":"{\n\t__schema{\n queryType {\n fields{\n name\n }\n }\n }\n}"}'
```

## Genrate caliban client code
```bash
# https://ghostdogpr.github.io/caliban/docs/client-codegen.html#generation-settings
sbt
calibanGenClient project/project/schema2.graphql src/main/scala/com/yen/Caliban4/Client.scala --genView true
```

## Concept
We need up to 3 case classes:
- one for for Queries: it will contain our read-only endpoints, those we express using GET requests in REST
- one for Mutations: it will contain endpoints for modifying data, those we express using POST, PUT and DELETE requests in REST
- one for Subscriptions: this is not supported by REST, it allows your backend to push events to the client, typically using a WebSocket
- Ref
	- https://medium.com/@ghostdogpr/graphql-in-scala-with-caliban-part-1-8ceb6099c3c2

## Ref
- General
	- EN
		- https://sangria-graphql.github.io/getting-started/
		- https://github.com/yennanliu/til/blob/master/README.md
	- https://www.jianshu.com/p/4ede220b713e
- Playground
	- https://github.com/sangria-graphql/sangria-playground
	- https://github.com/steveluscher/zero-to-graphql
- Online Playground
	- https://www.contentful.com/developers/docs/references/graphql/
- client example
	- https://www.howtographql.com/graphql-scala/2-preparing-first-query/
- sangria is graphsql server framework
	- https://gitter.im/sangria-graphql/sangria?at=57f827930aa72e3c5be35ace
- Tutorial
	- t1
		- https://medium.com/@ghostdogpr/graphql-in-scala-with-caliban-part-1-8ceb6099c3c2
			- code
				- https://github.com/ghostdogpr/caliban-blog-series
	- t2
		- https://dev.classmethod.jp/articles/sangria-akka-http-1/
			- code
				- https://github.com/katainaka0503/hello-sangria
	- t3
		- https://www.youtube.com/watch?v=lgxUKsOH65k
- caliban client projects
	- https://github.com/askmrsinh/typed-ghgraphql