FROM openjdk:11
COPY target/banking-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
