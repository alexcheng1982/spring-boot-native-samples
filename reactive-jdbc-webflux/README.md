# Reactive JDBC & WebFlux

A reactive microservice with REST API and RDBMS backend

Features:

* Spring Data R2dbc
* Spring WebFlux
* Flyway
* PostgreSQL
* Testcontainers

## Usage

1. Start PostgreSQL database using the `docker-compose.yml` file.
2. Build the native image using `mvn package -Pnative`.
3. Run the native image using `./target/reactive-jdbc-webflux`.
4. Test the service.

## Native image summary

* Size: 72MB
* Startup time: 5.5s
* Memory: 29.7MB 