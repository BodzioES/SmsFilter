FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer="rogozinskialbert@gmail.com"

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]