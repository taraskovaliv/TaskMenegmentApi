FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/taraskovaliv/TaskMenegmentApi.git

FROM maven:3.5-jdk-11
WORKDIR /
COPY --from=clone /app/TaskMenegmentApi /
RUN mvn swagger-codegen:generate
RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java","-jar","api/target/api-1.0-SNAPSHOT.jar", "server", "config.yml"]