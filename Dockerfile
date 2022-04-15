FROM openjdk:17-oracle
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} userServiceApp.jar
ENTRYPOINT ["java", "-jar", "userServiceApp.jar"]
