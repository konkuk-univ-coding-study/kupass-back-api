FROM openjdk:11

ARG JAR_FILE=./build/libs/kupass-back-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8443

ENTRYPOINT ["java", "-jar", "app.jar"]
