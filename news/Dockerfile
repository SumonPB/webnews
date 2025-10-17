FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY news-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9091

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
