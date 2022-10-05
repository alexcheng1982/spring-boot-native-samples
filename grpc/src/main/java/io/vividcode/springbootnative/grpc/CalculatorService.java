package io.vividcode.springbootnative.grpc;

import io.grpc.stub.StreamObserver;
import io.vividcode.springbootnative.grpc.CalculatorGrpc.CalculatorImplBase;
import io.vividcode.springbootnative.grpc.CalculatorOuterClass.CalculatorRequest;
import io.vividcode.springbootnative.grpc.CalculatorOuterClass.CalculatorResponse;
import org.springframework.stereotype.Component;

@Component
public class CalculatorService extends CalculatorImplBase {

  @Override
  public void calculate(
      CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
    double result =
        switch (request.getOperation()) {
          case ADD -> request.getNumber1() + request.getNumber2();
          case SUBTRACT -> request.getNumber1() - request.getNumber2();
          case MULTIPLY -> request.getNumber1() * request.getNumber2();
          case DIVIDE -> request.getNumber1() / request.getNumber2();
          case UNRECOGNIZED -> 0.0;
        };
    responseObserver.onNext(CalculatorResponse.newBuilder().setResult(result).build());
    responseObserver.onCompleted();
  }
}
