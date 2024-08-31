# Use the official Maven image to build the app
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean install

# Use the official OpenJDK image to run the app
FROM eclipse-temurin:17-jdk
COPY --from=build /target/SystemManagementSystem-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
