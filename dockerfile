# Stage 1: Build
FROM maven:4.0.0-rc-4-eclipse-temurin-17 AS build
WORKDIR /app

# Only copy Maven config and source code
COPY pom.xml .
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-alpine
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=build /app/target/library-management-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
# --- IGNORE ---