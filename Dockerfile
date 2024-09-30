# Use a base image with Java installed
FROM openjdk:17-jdk-slim

WORKDIR /app
# Set the working directory inside the container
ARG JAR_FILE=./target/*.jar

# Copy the JAR file into the container at /app
COPY ${JAR_FILE} movie-award.jar

# Expose the port your Spring Boot app listens on (default: 8080)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "movie-award.jar"]