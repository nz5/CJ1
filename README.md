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
