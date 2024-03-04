FROM ubuntu:latest as build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN app-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

#COPY --from=build /Users/guilhermecintracastro/Downloads/amafloraSpringApi/target/amafloraSpringApi-0.0.1-SNAPSHOT.jar
COPY --from=build /target/amafloraSpringApi-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar", "app.jar"]