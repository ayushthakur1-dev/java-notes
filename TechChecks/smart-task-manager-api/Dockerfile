# Use lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /java

COPY target/*.jar smart-task-manager-api-1.0-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","smart-task-manager-api-1.0-SNAPSHOT.jar"]