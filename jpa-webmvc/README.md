# Spring Data JPA with Web MVC

A microservice with REST API and RDBMS backend

Features:

* Spring Data JPA
* Spring Web MVC
* Flyway
* PostgreSQL
* Testcontainers
* OpenAPI doc & Swagger UI

## Usage

1. Start PostgreSQL database using the `docker-compose.yml` file.
2. Build the native image using `mvn package -Pnative`.
3. Run the native image using `./target/jpa-webmvc`.
4. Access Swagger UI (`http://localhost:8080/swagger-ui/4.5.0/index.html`) to test the service.

## Native image summary

* Size: 133MB
* Startup time: 5.5s
* Memory: 100.2MB 