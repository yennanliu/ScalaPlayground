FROM openjdk:8-jre
ADD target/pack/ /app/
EXPOSE 8080
ENTRYPOINT ["/bin/bash", "/app/bin/start-producer"]