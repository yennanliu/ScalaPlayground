## SBT Docker POC
- Create a simple Scala application via sbt above Docker

## Quick Start
```bash
# BasicExample
cd BasicExample
sbt docker
docker run -d --restart=always sbtdocker/basic-example
```

```bash
# akkahttp-docker-example
cd akkahttp-docker-example
sbt docker
docker run -d -p 9090:8080 --restart=always default/akkahttp-docker-example
```

## Ref
- https://velvia.github.io/Docker-Scala-Sbt/
- https://www.scala-sbt.org/sbt-native-packager/formats/docker.html
- https://github.com/marcuslonnberg/sbt-docker
- https://ithelp.ithome.com.tw/articles/10192036