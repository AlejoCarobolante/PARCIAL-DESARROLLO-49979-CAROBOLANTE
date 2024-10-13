# Usar OpenJDK 17 como base
FROM openjdk:17-jdk-alpine

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el wrapper de Gradle y los archivos necesarios
COPY gradlew ./
COPY gradle ./gradle

# Establecer permisos de ejecución para el wrapper de Gradle
RUN chmod +x gradlew

# Copiar el resto de los archivos del proyecto
COPY build/libs/*.jar /app/api-parcial.jar

# Comprobar los permisos y la presencia de gradlew (opcional, para depuración)
RUN ls -la

# Construir el proyecto usando Gradle
RUN ./gradlew build

#Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar tu aplicación (ajusta según tu configuración)
ENTRYPOINT ["sh", "./gradlew", "build"]

