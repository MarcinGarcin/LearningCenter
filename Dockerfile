FROM eclipse-temurin:17 AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/LearningCenter-0.1.jar /app/LearningCenter-0.1.jar
ENTRYPOINT ["java", "-jar", "/app/LearningCenter-0.1.jar"]
EXPOSE 8080
