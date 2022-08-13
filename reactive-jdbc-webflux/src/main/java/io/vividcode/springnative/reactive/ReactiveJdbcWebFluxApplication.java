package io.vividcode.springnative.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfig.class)
public class ReactiveJdbcWebFluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveJdbcWebFluxApplication.class, args);
  }
}
