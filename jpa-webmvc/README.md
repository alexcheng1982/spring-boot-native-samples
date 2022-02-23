# Spring Data JPA with WebMvc

A microservice with REST API and RDBMS backend

Features:

* Spring Data JPA
* Spring WebMvc
* Flyway
* PostgreSQL
* Testcontainers

## Usage

1. Build the native image using `mvn package -Pnative`.
2. Run the native image using `./target/jpa-webmvc`.
3. Access `http://localhost:8080/api/v1/user/1` to test the service.

## Native image summary

* Size: 105MB
* Startup time: 5.5s
* Memory: 100.2MB 