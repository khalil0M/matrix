FROM openjdk:11.0.5-jre-slim

EXPOSE 8080

ADD /target/matrix-0.0.2-SNAPSHOT.jar matrix-0.0.2-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","matrix-0.0.2-SNAPSHOT.jar"]