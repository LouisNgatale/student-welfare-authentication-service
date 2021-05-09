FROM openjdk:11
ADD target/authentication-service.jar authentication-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","authentication-service.jar"]
