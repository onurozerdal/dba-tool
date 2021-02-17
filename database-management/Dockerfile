FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE
ARG CONFIG_FOLDER
ADD ${JAR_FILE} app.jar
ADD ${CONFIG_FOLDER}/application-docker.properties application.properties

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8090