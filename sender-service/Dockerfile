# Alpine Linux with OpenJDK JRE
FROM maven:3-jdk-8-alpine

EXPOSE 8082

# copy jar into image
COPY target/sender-service-1.0.0.jar /usr/bin/sender-service.jar

# run application with this command line 
ENTRYPOINT ["java","-jar","/usr/bin/sender-service.jar"]
