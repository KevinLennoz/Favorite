FROM openjdk:11
VOLUME ["/tmp"]
ADD favorite.jar app.jar
EXPOSE 9090
ENTRYPOINT [ "sh","-c","java -jar /app.jar" ]
