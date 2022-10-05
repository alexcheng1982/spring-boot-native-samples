# gRPC Sample

gRPC

## Usage

1. Build the native image using `mvn package -Pnative`.
2. Run the native image using `./target/grpc`.
3. Use tools like [BloomRPC](https://github.com/bloomrpc/bloomrpc) to test the service.

## Native image summary

* Size: 51MB
* Startup time: 5.0s (7.8s for JVM mode)
* Memory: 30.3MB     (126.4MB for JVM mode)