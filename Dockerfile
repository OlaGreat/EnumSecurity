FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Enum-0.0.1-SNAPSHOT.jar Enum.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Enum.jar"]
