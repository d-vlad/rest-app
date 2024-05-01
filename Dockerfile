FROM maven:3.9.6-eclipse-temurin-21 as builder
LABEL authors="vladdulgheru"

COPY ./pom.xml ./pom.xml
COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:21.0.3_9-jre

COPY --from=builder ./target/rest-1.0-SNAPSHOT.jar ./rest-1.0-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080","rest-1.0-SNAPSHOT.jar"]