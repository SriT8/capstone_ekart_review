FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*jar
COPY ./target/NewReview-service-docker.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]