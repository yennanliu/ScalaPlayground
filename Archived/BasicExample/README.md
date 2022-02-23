# BasicExample

## Run
```bash
# BasicExample
cd BasicExample
sbt docker
docker run -d --restart=always sbtdocker/basic-example
# check
docker ps -a
docker logs -f <Container ID>
```

## Ref
- https://github.com/marcuslonnberg/sbt-docker/tree/master/examples/package-basic