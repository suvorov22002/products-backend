FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#COPY target/bootstrap.properties bootstrap.properties
ENTRYPOINT ["java","-jar","-Xmx64m","/app.jar"]