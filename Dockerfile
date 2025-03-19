FROM openjdk:21-slim
LABEL description="Spring Boot Employee Service"
LABEL authors="javacodewiz"
WORKDIR /app
COPY target/*.jar employee-service.jar
ENTRYPOINT ["java", "-jar","employee-service.jar"]