FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copias directamente el JAR que ya compilaste
COPY target/Eureka-0.0.1-SNAPSHOT.jar app.jar

# Puerto en el que Eureka corre por defecto
EXPOSE 8761
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]
