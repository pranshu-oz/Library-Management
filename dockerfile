FROM maven:4.0.0-rc-4-eclipse-temurin-17 AS build  
Copy . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-alpine
COPY --from=build target/library.management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]