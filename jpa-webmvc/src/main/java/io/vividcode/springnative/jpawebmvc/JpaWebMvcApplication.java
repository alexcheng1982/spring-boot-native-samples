package io.vividcode.springnative.jpawebmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(DefaultRuntimeHintsRegistrar.class)
public class JpaWebMvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpaWebMvcApplication.class, args);
  }
}
