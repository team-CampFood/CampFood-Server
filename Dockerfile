FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} campfood.jar
ENTRYPOINT ["java","-jar","/campfood.jar"]
