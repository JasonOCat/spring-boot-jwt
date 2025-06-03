FROM openjdk:21-jdk AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src src
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk
VOLUME /tmp

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]