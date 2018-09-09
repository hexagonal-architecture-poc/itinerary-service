# Itinerary service documentation

This microservice is responsible for data related to cities and itineraries (city, destiny city, departure time, arrival time) saved in a relational database. 

[Pipeline](https://gitlab.com/itinerary-challenge/itinerary-service/pipelines)
[Pipeline Documentation](https://gitlab.com/itinerary-challenge/itinerary-service/blob/master/docs/pipeline.md)

[Docker Registry](https://gitlab.com/itinerary-challenge/itinerary-service/container_registry)

--- 
## How to build 

This project was created with maven. To build the project execute the following command:

	$ mvn clean install

---
## How to run 

To run the application on standalone, execute the following command:

	$ mvn spring-boot:run

**Note:** Standalone mode will start a database in memory (H2).



To run the application with docker-compose or kubernetes access this [documentation](https://gitlab.com/itinerary-challenge/devops).

---
## How to use

To access the standalone access: [http://localhost:8080/itineraries/](http://localhost:8080/itineraries/)

The api documentation (Swagger): [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---
## Frameworks

- **SpringBoot(2.0):** Spring Boot makes it easy to create stand-alone applications. The Spring container provides an implementation for IoC supporting Dependency Injection.
- **SpringBoot Actuator:** SpringBoot’s Actuator which provides production ready features to help you monitor and manage your application.
	- This solution uses the health endpoint to check if the application is ready when deploying on kubernetes. (readinessProbe and livenessProbe)
- **Spring Data:** It makes it easy for the programming model for data access.
	- Every repository uses Spring data to access data from the database.
- **SpringBoot devtools:** Includes a set of tools to make the development experience easier. For example, Automatic Restart. When the application is launched with 'java -jar' all developer tools are disabled.
- **Liquibase:** Library responsible for versioning changes on database.
	- All scripts of this solution were made using Liquibase.
- **Sleuth:** Implements a distributed tracing solution.
	- Sleuth was used to easily track logs from different microservices.
	- With Zipkin, it's possible to track every microservice call. 
- **Swagger:** Creates a dynamic documentation for controllers.
	- Used to create a API documentation.

