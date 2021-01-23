FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD test-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/test.jar"]
