FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/taraskovaliv/TaskMenegmentApi.git

FROM maven:3.5-jdk-11
WORKDIR /app
COPY --from=clone /app/TaskMenegmentApi /app
RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/TaskMenegmentApi-1.0-SNAPSHOT.jar", "server", "/app/config.yml"]