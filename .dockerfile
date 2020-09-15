FROM openjdk:11

ARG JAR_FILE=target/TaskMenegmentApi-1.0-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/classes/

WORKDIR /usr/local/runme

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080