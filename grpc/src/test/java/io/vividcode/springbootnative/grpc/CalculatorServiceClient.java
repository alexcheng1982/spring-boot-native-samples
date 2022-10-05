package io.vividcode.springbootnative.grpc;

import io.vividcode.springbootnative.grpc.CalculatorOuterClass.CalculatorRequest;
import io.vividcode.springbootnative.grpc.CalculatorOuterClass.CalculatorRequest.OperationType;
import io.vividcode.springbootnative.grpc.CalculatorOuterClass.CalculatorResponse;

public class CalculatorServiceClient {

  private final CalculatorService calculatorService;

  public CalculatorServiceClient(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  public double calculate(OperationType operationType, double op1, double op2) {
    CalculatorRequest request =
        CalculatorRequest.newBuilder()
            .setOperation(operationType)
            .setNumber1(op1)
            .setNumber2(op2)
            .build();
    TestStreamObserver<CalculatorResponse> streamObserver = new TestStreamObserver<>();
    calculatorService.calculate(request, streamObserver);
    return streamObserver.getValues().get(0).getResult();
  }
}
