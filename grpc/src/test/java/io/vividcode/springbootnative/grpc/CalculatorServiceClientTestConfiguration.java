package io.vividcode.springbootnative.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorServiceClientTestConfiguration {

  @Bean
  public CalculatorService calculatorService() {
    return new CalculatorService();
  }

  @Bean
  public CalculatorServiceClient calculatorServiceClient(CalculatorService calculatorService) {
    return new CalculatorServiceClient(calculatorService);
  }
}
