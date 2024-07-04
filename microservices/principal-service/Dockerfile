FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/principal-service-0.0.1-SNAPSHOT.jar principal-service.jar
ENTRYPOINT ["java", "-jar", "principal-service.jar"]