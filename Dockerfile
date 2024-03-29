# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY ./target/user-task-mngt-1.0.0.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Define environment variable
ENV JAVA_OPTS=""

# Run java command to start your application
CMD ["java", "-jar", "app.jar"]
