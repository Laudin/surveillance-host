FROM eclipse-temurin:22-jdk-alpine

COPY target/host-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# docker image build -t docker-java-jar:latest .

# docker run -p 8080:8080 docker-java-jar