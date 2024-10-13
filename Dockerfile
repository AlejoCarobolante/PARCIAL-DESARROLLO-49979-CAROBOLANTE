
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ParcialDesarrollo-0.0.1-SNAPSHOT.jar /app/api-parcial.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/mi-api.jar"]
