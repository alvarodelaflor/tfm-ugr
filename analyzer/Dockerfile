FROM openjdk:17-jdk-alpine
COPY target/analyzer-0.0.1-SNAPSHOT.jar analyzer-app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=*:8091,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "analyzer-app.jar"]
EXPOSE 8081
