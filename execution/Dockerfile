FROM openjdk:17-jdk-alpine
COPY target/execution-0.0.1-SNAPSHOT.jar execution-app.jar
ENTRYPOINT ["java", "--add-opens", "java.base/java.time=ALL-UNNAMED", "-agentlib:jdwp=transport=dt_socket,address=*:8093,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "execution-app.jar"]
EXPOSE 8083
