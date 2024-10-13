FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copia el código fuente al contenedor
COPY . /app

# Ejecuta Gradle para construir el JAR dentro del contenedor
RUN ./gradlew build

# Verifica que el archivo JAR fue creado correctamente
RUN ls -l build/libs/

# Copia el archivo JAR generado al directorio de trabajo
COPY build/libs/*.jar /app/api-parcial.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/api-parcial.jar"]
