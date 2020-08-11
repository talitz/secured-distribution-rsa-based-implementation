# Secured Distribution RSA Based Implementation

<img src="https://i.ibb.co/gZ45j0T/Screen-Shot-2020-08-09-at-22-53-52.png" align="center">

### Introduction:
Complete me.
- <b>Complete me</b> - Complete me.
- <b>Complete me</b> - Complete me. 
- <b>Complete me</b> - Complete me.
- <b>Complete me</b> - Complete me.

### Prerequisites:

- Docker 19.03.12. 
- Java 13.0.2.
- Maven 3.6.3.
- The following ports should be open before running the project: 8080, 8081, 8082.

### Running the Project:

Please <b>strictly</b> follow each step of the following:

1) Clone the project to your local directory:</br> ```git clone https://github.com/talitz/secured-distribution-rsa-based-implementation.git```.

2) CD into the directory: ```cd secured-distribution-rsa-based-implementation```.

3) Provide permissions to the shell running scripts: ```chmod 755 *.yml```.

5) Run all Micro-Services: ```sudo docker-compose up -d```.

6) Check the status using ```sudo docker-compose ps -a```.

You are now ready to go. 

### Basic Communication with Distributer:
Test the distributer micro-service and try to extract the public key that is accessible to everyone:</br>
```curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/distributer-service/public-key```

Send a file as a string and get the relevant signatrue:</br>
```curl -d "fileAsString=value1" -X POST http://localhost:8080/distributer-service/signature```

### 1st Scenario - Sending a Real File:
- Sending a file with the sender microservice can be done with the following POST request:</br>
```curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -data="fileAsString=This-is-real-file" http://localhost:8082/sender-service/send-file```

### 2nd Scenario - Sending a Fake File from a Man in the Middle:

7) Terminate the project using: ```sudo docker-compose down```.
