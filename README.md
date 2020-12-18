## SBT Docker POC
- Create a simple Scala application via sbt above Docker

## Quick Start
```bash
# BasicExample
cd BasicExample
sbt docker
docker run -d --restart=always sbtdocker/basic-example
# check
docker ps -a
docker logs -f <Container ID>
```

```bash
# akkahttp-docker-example
cd akkahttp-docker-example
sbt docker
docker run -d -p 9090:8080 --restart=always default/akkahttp-docker-example
# check
curl localhost:9090/hello
```

```bash 
# Finatra-docker-example
cd Finatra-docker-example
sbt docker
docker run -d -p 8888:8888 --restart=always default/finatra-docker-example
# check
curl ocalhost:8888/hello
curl localhost:8080/wazzup
```

## Ref
- https://velvia.github.io/Docker-Scala-Sbt/
- https://www.scala-sbt.org/sbt-native-packager/formats/docker.html
- https://github.com/marcuslonnberg/sbt-docker
- https://ithelp.ithome.com.tw/articles/10192036