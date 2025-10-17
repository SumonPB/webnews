FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/subscription-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9092

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
