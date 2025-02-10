FROM openjdk:17
VOLUME /tmp
COPY /target/*.jar rms.jar
ENTRYPOINT ["java", "-jar", "rms.jar"]