# Spring Boot 3.0 Native Samples

![build](https://github.com/alexcheng1982/spring-boot-native-samples/actions/workflows/build.yml/badge.svg)

Samples of using Spring Boot 3.0 to build native executables

* Java 17 required (GraalVM Java 17 required for `native` profile)
* Spring Boot 3.0.0
* Tested with GraalVM 22.3.0 Java 17 CE

## Samples

> Metrics captured on Windows 10 with GraalVM 22.3.0 Java 17 CE

### gRPC

Simple gRPC service

| Mode | Binary size | Startup time | Memory |
|--------|-------|-------|--|
| JVM | 21.5 (JAR) | 0.96s | 96MB |
| Native | 47.8MB | 0.124s | 24.8MB |

### Spring Data JPA with WebMvc

A microservice with REST API and RDBMS backend

| Mode | Binary size | Startup time | Memory |
|--------|-------|-------|--|
| JVM | 42.7 (JAR) | 2.72s | 183MB |
| Native | 129MB | 0.277s | 62.3MB |

Features:

* Spring Data JPA
* Spring Web MVC
* Flyway
* PostgreSQL
* Testcontainers

### Reactive JDBC & WebFlux

A reactive microservice with REST API and RDBMS backend

| Mode | Binary size | Startup time | Memory |
|--------|-------|-------|--|
| JVM | 32.1 (JAR) | 1.929s | 142.7MB |
| Native | 79.4MB | 0.217s | 56.9MB |

Features:

* Spring Data R2dbc
* Spring WebFlux
* Flyway
* PostgreSQL
* Testcontainers
