package io.vividcode.springbootnative.grpc;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.protobuf.services.HealthStatusManager;
import io.grpc.protobuf.services.ProtoReflectionService;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class GrpcServer implements SmartLifecycle {

  private static final AtomicInteger serverCounter = new AtomicInteger(-1);
  private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServer.class);

  private final CalculatorService calculatorService;
  private final int port;
  private Server server;

  public GrpcServer(@Value("${grpc.server.port:6565}") int port,
      CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
    this.port = port;
  }

  @Override
  public void start() {
    try {
      createAndStartGrpcServer();
    } catch (final IOException e) {
      throw new IllegalStateException("Failed to start the grpc server", e);
    }
  }

  @Override
  public boolean isAutoStartup() {
    return true;
  }

  @Override
  public void stop() {
    stopAndReleaseGrpcServer();
  }

  @Override
  public int getPhase() {
    return Integer.MAX_VALUE;
  }

  @Override
  public boolean isRunning() {
    return this.server != null && !this.server.isShutdown();
  }

  private void createAndStartGrpcServer() throws IOException {
    if (server != null) {
      return;
    }
    server = NettyServerBuilder.forPort(port)
        .addService(new HealthStatusManager().getHealthService())
        .addService(ProtoReflectionService.newInstance())
        .addService(calculatorService).build();
    server.start();
    LOGGER.info("Server started, listening on {}", server.getPort());
    final Thread awaitThread = new Thread(() -> {
      try {
        server.awaitTermination();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
    awaitThread.setName("grpc-server-container-" + (serverCounter.incrementAndGet()));
    awaitThread.setDaemon(false);
    awaitThread.start();
  }

  private void stopAndReleaseGrpcServer() {
    final Server localServer = this.server;
    if (localServer != null) {
      LOGGER.debug("Initiating gRPC server shutdown");
      localServer.shutdown();
      try {
        localServer.awaitTermination(30, TimeUnit.SECONDS);
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      } finally {
        localServer.shutdownNow();
        this.server = null;
      }
      LOGGER.info("Completed gRPC server shutdown");
    }
  }
}
