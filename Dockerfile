
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN adduser -D ecommerce
USER ecommerce

COPY --from=builder --chown=ecommerce:ecommerce /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
