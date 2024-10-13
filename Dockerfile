# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo gradlew y gradlew.bat
COPY gradlew ./
COPY gradle ./gradle

# Cambia los permisos para permitir la ejecución
RUN chmod +x gradlew

# Copia el resto de la aplicación
COPY . .

# Ejecuta Gradle para construir el JAR dentro del contenedor
RUN ./gradlew build

# Copia el archivo JAR generado al directorio de trabajo
COPY build/libs/*.jar /app/api-parcial.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "api-parcial.jar"]

