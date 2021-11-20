## SBT Docker POC
- Create various Scala POC apps with/without sbt Docker

## Quick Start
<details>
<summary>Quick Start</summary>

- [Basic Example](./BasicExample)
- [Akka app Docker](./akkahttp-docker-example)
- [Finatra app Docker](./Finatra-docker-example)
- [urlShortener](./urlShortener) : URL shortener app
- [CRMApI](./CRMApI) : CRM demo API

## Inin project
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