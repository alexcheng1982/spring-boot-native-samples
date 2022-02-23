package io.vividcode.springnative.grpc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.vividcode.springnative.grpc.CalculatorOuterClass.CalculatorRequest.OperationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest(
        properties = {
                "grpc.server.port=8888",
                "grpc.client.calculatorService.address=localhost:8888",
                "grpc.client.calculatorService.negotiationType=PLAINTEXT"
        }
)
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
