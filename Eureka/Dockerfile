FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiamos el JAR
COPY Eureka-0.0.1-SNAPSHOT.jar app.jar

# Declaramos el puerto dinámico que Render asigna
ENV PORT=${PORT:-9095}
EXPOSE ${PORT}

# Arranca la app usando la variable PORT
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
