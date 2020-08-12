# Secured Distribution RSA Based Implementation

<img src="https://i.ibb.co/gZ45j0T/Screen-Shot-2020-08-09-at-22-53-52.png" align="center">

*Submitted by Tal Yitzhak and Raphael Zanzouri.

### Introduction:

We implemented the project so that each square represents a different microservis (runs in a different process) that implements the REST API, which enables the communication between the different microservices.

- "Sender" - represents the source, which wants to send a file to the "Receiver".

- "Distributor" - represents a microservice that is a "service person" who manages the file distribution operation. That is, it manages the encryption, holds in its hand the public and private key and allows access to the public key to anyone who requests using the endpoint we implemented for the REST API.

- "Receiver" - represents the destination, which wants to receive the file from the "Sender".

The "sender" and "recipient" can, with the help of a GET call, receive the public key from the "distributor".
The "distributor" also uses a class that implements the functionality of the RSA encryption algorithm, which we implemented in Java class.
The various microservices talk to each other using the REST API.

You can run the project in 3 different processes, as Java applications, and you can also pack them in Docker Containers, and run the project in one command using Docker-Compose.

### Prerequisites:

- Docker 19.03.12. 
- Docker Compose 1.26.2.
- Java 13.0.2.
- Maven 3.6.3.
- The following ports should be open before running the project: 8080, 8081, 8082.

### Running the Project:

Please <b>strictly</b> follow each step of the following:

1) Clone the project to your local directory:</br> ```git clone https://github.com/talitz/secured-distribution-rsa-based-implementation.git```.

2) CD into the directory: ```cd secured-distribution-rsa-based-implementation```.

3) Build the project using Maven: ```mvn clean install```.

4) Provide permissions to the shell running scripts: ```chmod 755 *.yml```.

5) Run all Micro-Services: ```sudo docker-compose up --build --force-recreate --no-deps```.

6) Check the status using ```sudo docker-compose ps -a```.

7) Terminate the project using: ```sudo docker-compose down```.

### Basic Communication with Distributer:
Test the distributer micro-service and try to extract the public key that is accessible to everyone:</br>
```curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/distributer-service/public-key```

Send a file as a string and get the relevant signatrue:</br>
```curl -d "fileAsString=value1" -X POST http://localhost:8080/distributer-service/signature```

### 1st Scenario - Sending a Real File:
- Sending a file with the sender microservice can be done with the following POST request:</br>
```curl -d "fileAsString=value1" -X POST http://localhost:8082/sender-service/send-file```

### 2nd Scenario - Sending a modified File:
In this scenario, the file sent to the receiver is slightly modified after generating the signature (added 1 char to the string)
- Sending a file with the sender microservice can be done with the following POST request:</br>
```curl -d "fileAsString=value1" -X POST http://localhost:8082/sender-service/send-file-corrupted```
