FROM openjdk:11-jre
ENV profile=default
COPY target/dependency-jars /run/dependency-jars
ADD target/application.jar /run/application.jar
EXPOSE $APP_PORT
ENTRYPOINT ["java", "-Dspring.profiles.active=${profile}", "-jar", "run/application.jar"]
