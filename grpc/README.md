# gRPC Sample

gRPC

## Usage

1. Build the native image using `mvn package -Pnative`.
2. Run the native image using `./target/grpc`.
3. Use tools like [BloomRPC](https://github.com/bloomrpc/bloomrpc) to test the service.

## Native image summary

* Size: 47.8MB
* Startup time: 0.124s (0.96s for JVM mode)
* Memory: 24.8MB     (96MB for JVM mode)