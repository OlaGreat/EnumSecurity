FROM maven:3.9.5-openjdk-21
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/Enum-0.0.1-SNAPSHOT.jar Enum.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/Enum.jar"]
