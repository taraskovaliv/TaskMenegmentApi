FROM maven:3.5-jdk-11
COPY . .
RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java","-jar","/target/TaskMenegmentApi-1.0-SNAPSHOT.jar", "server", "config.yml"]