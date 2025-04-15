# Use a lightweight OpenJDK base image
FROM openjdk:23


# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/customer-service.jar customer-service.jar

# Expose the application port (change if needed)
EXPOSE 5554

# Run the JAR file
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
