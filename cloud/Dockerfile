FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#ADD test-0.0.1-SNAPSHOT.jar test.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","tmp/test.jar"]
CMD java -jar /tmp/eureka-service.jar \
    & java -jar /tmp/eureka-client.jar \
    & java -jar /tmp/cloud.jar