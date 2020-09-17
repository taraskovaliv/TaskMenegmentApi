FROM openjdk:11
WORKDIR /app
COPY /target/TaskMenegmentApi-1.0-SNAPSHOT.jar /app/app.jar
COPY config.yml /app/config.yml

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar", "server", "/app/config.yml"]