# Spring Data JPA with Web MVC

A microservice with REST API and RDBMS backend

Features:

* Spring Data JPA
* Spring Web MVC
* Flyway
* PostgreSQL
* Testcontainers
* OpenAPI doc

## Usage

1. Start PostgreSQL database using the `docker-compose.yml` file.
2. Build the native image using `mvn package -Pnative`.
3. Run the native image using `./target/jpa-webmvc`.
4. Test the service.

## Native image summary

* Size: 132MB
* Startup time: 5.5s
* Memory: 100.2MB 