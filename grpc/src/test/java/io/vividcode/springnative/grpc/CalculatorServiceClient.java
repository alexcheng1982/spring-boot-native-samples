package io.vividcode.springnative.grpc;

import io.vividcode.springnative.grpc.CalculatorGrpc.CalculatorBlockingStub;
import io.vividcode.springnative.grpc.CalculatorOuterClass.CalculatorRequest;
import io.vividcode.springnative.grpc.CalculatorOuterClass.CalculatorRequest.OperationType;
import io.vividcode.springnative.grpc.CalculatorOuterClass.CalculatorResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class CalculatorServiceClient {

  @GrpcClient("calculatorService")
  CalculatorBlockingStub calculatorService;

  public double calculate(OperationType operationType, double op1, double op2) {
    CalculatorRequest request =
        CalculatorRequest.newBuilder()
            .setOperation(operationType)
            .setNumber1(op1)
            .setNumber2(op2)
            .build();
    CalculatorResponse response = calculatorService.calculate(request);
    return response.getResult();
  }
}
