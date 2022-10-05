package io.vividcode.springbootnative.grpc;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

public class TestStreamObserver<T> implements StreamObserver<T> {

  private List<T> values = new ArrayList<>();
  private Throwable error;


  @Override
  public void onNext(T value) {
    values.add(value);
  }

  @Override
  public void onError(Throwable t) {
    error = t;
  }

  @Override
  public void onCompleted() {

  }

  public List<T> getValues() {
    return values;
  }

  public Throwable getError() {
    return error;
  }
}
