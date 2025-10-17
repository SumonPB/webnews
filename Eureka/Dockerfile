FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiamos el JAR que pusiste dentro de la carpeta Eureka
COPY Eureka-0.0.1-SNAPSHOT.jar app.jar

# Declaramos el puerto (Render lo usa automáticamente)
ENV PORT=8761
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]
