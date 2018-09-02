CJ1 Information Engineering Website
===================================
Description..

# Architecture
Maven is used to build
  2 modules: frontend, backend



# Instructions
## Development mode
In development mode, the backend is packaged without the frontend.
Backend: Port: 8080 (default), Cors: enabled for frontend (in code)
Frontend: Port: 4200 (default), makes requests based on proxy.conf.json, to the backends port. API endpoints as constants.
  Source: https://github.com/angular/angular-cli/blob/master/docs/documentation/stories/proxy.md

### Requirements
1. Node
2. Npm
3. Maven

### Build
```bash
# Backend: build the server module without the client jar dependency 
# (can be run from the root or from the 'server' directories)
mvn clean install

# Frontend: install client's npm dependencies (it's necessary for the first build only)
# navigate to the 'frontend' directory and run the following command
npm install
```

### Run
```bash
# Backend: navigate to the 'server' directory and run the following command
mvn spring-boot:run

# Frontend:
ng serve -o
```

> Access Backend  at [http://localhost:8080](http://localhost:8080)
> Access Frontend at [http://localhost:4200](http://localhost:4200)



### Production mode
In production mode, the frontend is packaged into the .jar of the backend. 
The frontend makes requests based on the base-href property.

#### Requirements
1. Java
2. Maven (optional, required for building)

### Build
```bash
# build both server and client
## if maven not installed, use ./mvnw or mvnw.cmd
mvn clean install -Pprod
```
### Run
```bash
java -jar backend/target/backend-1.0.jar
# or
# Navigate to the 'server' directory and run the following command
mvn spring-boot:run -Pprod
```

The application will be accessible at `http://localhost:8080`.



# Development of an OSM integrated web application

Project for creating and managing events for HAW students

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Angular](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Tomcat](https://rometools.github.io/rome/) - Server to run backend
 

## Authors

* **Oleksii Skorykh**
* **Lorant Vrînceanu**
* **Italo Martins**
* **Nizami Zamanov**


## Acknowledgments

* Thanks to Prof. Dr.-Ing. Lutz Leutelt for idea and support during project 
* Inspiration
* etc
