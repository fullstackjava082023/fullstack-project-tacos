FROM openjdk:21

# Copy the jar file
COPY target/fullstack-project-tacos-0.0.1-SNAPSHOT.jar /fullstack-project-tacos-0.0.1-SNAPSHOT.jar


# Entry point run the jar file
ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar /fullstack-project-tacos-0.0.1-SNAPSHOT.jar"]
