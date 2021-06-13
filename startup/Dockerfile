FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#ADD test-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","tmp/test.jar"]
