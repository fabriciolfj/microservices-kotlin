FROM adoptopenjdk/openjdk11

EXPOSE 8080

ADD maven/*.jar app.jar

ENTRYPOINT ["java", "-jar","/app.jar"]