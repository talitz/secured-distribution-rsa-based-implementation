# Alpine Linux with OpenJDK JRE
FROM maven:3-jdk-8-alpine

EXPOSE 8080

# copy jar into image
COPY target/distributer-service-1.0.0.jar /usr/bin/distributer-service.jar

# run application with this command line 
ENTRYPOINT ["java","-jar","/usr/bin/distributer-service.jar"]
