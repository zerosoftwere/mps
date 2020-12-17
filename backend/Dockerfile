FROM maven:3.6.3-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11.0.8-jre-slim
VOLUME /tmp
COPY --from=build /usr/src/app/target/mps-1.0.0-SNAPSHOT-runner.jar /usr/app/mps-1.0.0-SNAPSHOT-runner.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/app/mps-1.0.0-SNAPSHOT-runner.jar"]