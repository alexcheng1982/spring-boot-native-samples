# Spring Native Samples

Samples of using Spring Native

* Java 17 required (GraalVM Java 17 required for `native` profile)
* Tested with GraalVM 22.0.0.2 Java 17 CE

## gRPC

gRPC using [grpc-spring-boot-starter](https://github.com/yidongnan/grpc-spring-boot-starter)

1. Build the native image using `make grpc-native`.
2. Run the native image using `./grpc/target/grpc`.
3. Use tools like [BloomRPC](https://github.com/bloomrpc/bloomrpc) to test the service.

Native image summary:

* Size: 51MB
* Startup time: 5.0s (7.8s for JVM mode)
* Memory: 30.3MB     (126.4MB for JVM mode)