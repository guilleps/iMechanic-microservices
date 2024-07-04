# Dockerfile for Discovery Server
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
