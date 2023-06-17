FROM openjdk:17-jdk-alpine
COPY target/redis-gateway-0.0.1-SNAPSHOT.jar redis-gateway-app.jar
ENTRYPOINT ["java", "--add-opens", "java.base/java.time=ALL-UNNAMED", "-agentlib:jdwp=transport=dt_socket,address=*:8092,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "redis-gateway-app.jar"]
EXPOSE 8082
