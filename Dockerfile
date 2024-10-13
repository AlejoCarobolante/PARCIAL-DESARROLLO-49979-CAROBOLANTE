FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copia el código fuente al contenedor
COPY . /app

# Ejecuta Maven para construir el JAR dentro del contenedor
RUN ./mvnw clean package

# Copia el archivo JAR generado al directorio de trabajo
COPY target/ParcialDesarrollo-0.0.1-SNAPSHOT.jar /app/api-parcial.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/api-parcial.jar"]
