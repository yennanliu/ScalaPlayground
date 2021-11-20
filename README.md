## SBT Docker POC
- Create various Scala POC apps with/without sbt Docker

## Quick Start
<details>
<summary>Quick Start</summary>

- [Basic Example](./BasicExample)
```bash
# BasicExample
cd BasicExample
sbt docker
docker run -d --restart=always sbtdocker/basic-example
# check
docker ps -a
docker logs -f <Container ID>
```

- [Akka app Docker](./akkahttp-docker-example)
```bash
# akkahttp-docker-example
cd akkahttp-docker-example
sbt docker
docker run -d -p 9090:8080 --restart=always default/akkahttp-docker-example
# check
curl localhost:9090/hello

```
- [Finatra app Docker](./Finatra-docker-example)
```bash 
# Finatra-docker-example
cd Finatra-docker-example
#sbt docker
sbt docker:publishLocal
docker images
#docker run -it ef3371fa8946 # docker run -it <image_id>
docker run -d -p 8888:8888 --restart=always <image_id>
#docker run -d -p 9090:8080 --restart=always default/akkahttp-docker-example

# check
curl localhost:8888/hello
curl localhost:8888/wazzup
#curl localhost:9990

# check log
docker ps -a 
docker logs -f <image_id>

# manually
sbt clean compile
sbt clean assembly
#java -cp target/scala-2.11/FinatraHelloWorld-assembly-1.0.jar com.twitter.dev.HelloWorld
java -cp target/scala-2.11/FinatraHelloWorld-assembly-1.0.jar com.twitter.server.FinatraApp
```

- [urlShortener](./urlShortener) : URL shortener app
- [CRMApI](./CRMApI) : CRM demo API

## Inin the project
```bash
# make a prpject /ScalaScraper
cd sbtDockerPOC
mkdir ScalaScraper 
cd ScalaScraper
sbt new scala/scala-seed.g8 -o app
# then the file structure should look like below, if success

```
</details>

## Ref
<details>
<summary>Ref</summary>

- sbt Docker
	- https://velvia.github.io/Docker-Scala-Sbt/
	- https://www.scala-sbt.org/sbt-native-packager/formats/docker.html
	- https://github.com/marcuslonnberg/sbt-docker
	- https://ithelp.ithome.com.tw/articles/10192036
- Scala works with json
	- https://mungingdata.com/scala/read-write-json/
	- https://github.com/lihaoyi/os-lib#getting-started
- Akka
	- Intro
		- https://www.baeldung.com/scala/category/akka 
	- example code
		- https://github.com/akka/akka-platform-guide/tree/main/docs-source/docs/modules/how-to/examples
		- https://github.com/yennanliu/akka-sample-cluster-docker-compose-scala
- Plat framework (prod ready framework, like django)
	- Intro
		- https://www.baeldung.com/scala/category/play-framework
		- https://www.playframework.com/

</details>
