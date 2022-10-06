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

* Size: 123MB
* Startup time: 0.237s (2.83s for JVM mode)
* Memory: 64.6MB (183MB for JVM mode)