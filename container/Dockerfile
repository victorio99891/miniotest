FROM openjdk:11

MAINTAINER Wiktor Krzyżanowski <victorio99891@wp.pl>

WORKDIR /application

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5500

EXPOSE 5500

COPY minioapi.jar /app/minioapi.jar

EXPOSE 8080

ENV MINIO_URL http://192.168.128.128:9080

ENV MINIO_USER minio

ENV MINIO_PASSWORD Armagedon#14

CMD ["/bin/sh", "-c", "java -jar /app/minioapi.jar"]