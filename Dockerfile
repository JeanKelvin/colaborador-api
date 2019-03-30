FROM openjdk:8-alpine

MAINTAINER Jean Kelvin

COPY target/*.jar colaborador-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/colaborador-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080