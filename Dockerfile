
FROM amazoncorretto:21-alpine AS builder 
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean install -DskipTests

FROM amazoncorretto:21-alpine
WORKDIR /app

RUN adduser -D ecommerce
USER ecommerce

COPY --from=builder --chown=ecommerce:ecommerce /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
