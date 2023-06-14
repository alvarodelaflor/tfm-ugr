FROM openjdk:17-jdk-alpine
COPY target/sensors-0.0.1-SNAPSHOT.jar sensors-app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:8090,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "sensors-app.jar"]
EXPOSE 8080
