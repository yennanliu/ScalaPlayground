# Finatra app Docker

## Run
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