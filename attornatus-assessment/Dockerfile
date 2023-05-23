FROM openjdk:20-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} attornatus.jar
ENTRYPOINT ["java","-jar","/attornatus.jar"]