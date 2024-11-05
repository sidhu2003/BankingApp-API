#stage 1

FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

#stage 2

FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8180