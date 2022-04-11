# Graphql POC

## Quick start
```bash
# run the graphql server
cd zero-to-graphql/zero-scala
sbt run

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
--data '{ "query": "{ allPeople { id fullName } } " }' \
http://localhost:8080/graphql
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