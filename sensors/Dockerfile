FROM openjdk:17-jdk-alpine
COPY target/sensors-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:8081,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "java-app.jar"]
EXPOSE 8080
