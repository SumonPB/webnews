FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY client-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
