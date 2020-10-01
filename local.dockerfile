FROM maven:3.5-jdk-11
COPY . .
RUN mvn clean
RUN mvn swagger-codegen:generate
RUN mvn package

EXPOSE 8080

ENTRYPOINT ["java","-jar","/target/TaskMenegmentApi-1.0-SNAPSHOT.jar", "server", "config.yml"]