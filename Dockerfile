FROM maven:3.9.6-eclipse-temurin-22 AS build
COPY pom.xml .
COPY src /src
RUN mvn package

# FROM eclipse-temurin:22-jdk-alpine
# VOLUME /tmp
# COPY --from=build target/app*.jar app.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]

FROM eclipse-temurin:22-jdk-alpine 

COPY --from=build target/host-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# docker image build -t docker-java-jar:latest .

# docker run -p 8080:8080 docker-java-jar:latest