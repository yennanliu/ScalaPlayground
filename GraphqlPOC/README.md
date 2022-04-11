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
- Tutorial
	- t1
		- https://medium.com/@ghostdogpr/graphql-in-scala-with-caliban-part-1-8ceb6099c3c2
			- code
				- https://github.com/ghostdogpr/caliban-blog-series
	- t2
		- https://dev.classmethod.jp/articles/sangria-akka-http-1/
			- code
				- https://github.com/katainaka0503/hello-sangria