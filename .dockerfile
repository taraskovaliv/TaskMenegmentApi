FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/taraskovaliv/TaskMenegmentApi.git

FROM maven:3.5-jdk-11 as build
WORKDIR /app
COPY --from=clone /app/TaskMenegmentApi /app
RUN mvn package

FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/TaskMenegmentApi-1.0-SNAPSHOT.jar /app/app.jar
RUN git clone https://github.com/taraskovaliv/TaskMenegmentApi.git

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar", "server", "TaskMenegmentApi/config.yml"]