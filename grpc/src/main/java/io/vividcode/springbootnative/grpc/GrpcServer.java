package io.vividcode.springbootnative.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GrpcServer {

  private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServer.class);

  private final Server server;

  public GrpcServer(@Value("${grpc.server.port:6565}") int port,
      CalculatorService calculatorService) {
    server = ServerBuilder.forPort(port).addService(calculatorService).build();
  }

  @EventListener(ApplicationStartedEvent.class)
  public void autoStart() throws IOException, InterruptedException {
    start();
    blockUntilShutdown();
  }

  private void start() throws IOException {
    server.start();
    LOGGER.info("Server started, listening on {}", server.getPort());
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.err.println("shutting down gRPC server since JVM is shutting down");
      try {
        GrpcServer.this.stop();
      } catch (InterruptedException e) {
        e.printStackTrace(System.err);
      }
      System.err.println("server shut down");
    }));
  }

  public void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
}
