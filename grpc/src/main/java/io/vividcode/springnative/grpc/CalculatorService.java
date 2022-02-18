package io.vividcode.springnative.grpc;

import io.grpc.stub.StreamObserver;
import io.vividcode.springnative.grpc.CalculatorOuterClass.CalculatorRequest;
import io.vividcode.springnative.grpc.CalculatorOuterClass.CalculatorResponse;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CalculatorService extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void calculate(CalculatorRequest request,
            StreamObserver<CalculatorResponse> responseObserver) {
        double result = switch (request.getOperation()) {
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
