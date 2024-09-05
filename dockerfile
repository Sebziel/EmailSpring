FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/*.jar /app/email-spring.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "email-spring.jar"]