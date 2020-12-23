## SBT Docker POC
- Create a simple Scala application via sbt above Docker

## Quick Start
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

# manually
sbt clean compile
sbt clean assembly
#java -cp target/scala-2.11/FinatraHelloWorld-assembly-1.0.jar com.twitter.dev.HelloWorld
java -cp target/scala-2.11/FinatraHelloWorld-assembly-1.0.jar com.twitter.server.FinatraApp
```

## Ref
- https://velvia.github.io/Docker-Scala-Sbt/
- https://www.scala-sbt.org/sbt-native-packager/formats/docker.html
- https://github.com/marcuslonnberg/sbt-docker
- https://ithelp.ithome.com.tw/articles/10192036