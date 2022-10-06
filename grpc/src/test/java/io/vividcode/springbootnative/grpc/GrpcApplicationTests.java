package io.vividcode.springbootnative.grpc;

import static org.assertj.core.api.Assertions.assertThat;

import io.vividcode.springbootnative.grpc.CalculatorOuterClass.CalculatorRequest.OperationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest
@SpringJUnitConfig(classes = {CalculatorServiceClientTestConfiguration.class})
@DirtiesContext
@DisplayName("gRPC")
class GrpcApplicationTests {

  @Autowired
  CalculatorServiceClient client;

  @Test
  @DisplayName("Calculator")
  void calculatorService() {
    assertThat(client.calculate(OperationType.ADD, 1.0, 1.0)).isEqualTo(2.0);
    assertThat(client.calculate(OperationType.SUBTRACT, 1.0, 1.0)).isEqualTo(0.0);
    assertThat(client.calculate(OperationType.MULTIPLY, 1.0, 1.0)).isEqualTo(1.0);
    assertThat(client.calculate(OperationType.DIVIDE, 1.0, 1.0)).isEqualTo(1.0);
  }
}
