FROM amazoncorretto:17-alpine-jdk
EXPOSE 3001
COPY target/tree-service-1.0.jar tree-service-1.0.jar
ENTRYPOINT ["java","-jar","/tree-service-1.0.jar"]