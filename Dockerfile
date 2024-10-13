# Usar OpenJDK 17 como base
FROM openjdk:17-jdk-alpine

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el archivo gradlew y el directorio gradle
COPY gradlew ./
COPY gradle ./gradle

# Establecer permisos de ejecución para el wrapper de Gradle
RUN chmod +x gradlew

# Copiar el resto de los archivos del proyecto
COPY . .

# Construir el proyecto usando Gradle
RUN ./gradlew build

# Copiar el archivo JAR generado al directorio de la aplicación
COPY build/libs/*.jar /app/api-parcial.jar

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "/app/api-parcial.jar"]


