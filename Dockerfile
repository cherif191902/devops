FROM openjdk:17-jdk-alpine
EXPOSE 8089
COPY target/adoption-Project-0.0.1-SNAPSHOT.jar adoption.jar
ENTRYPOINT ["java","-jar","adoption.jar"]