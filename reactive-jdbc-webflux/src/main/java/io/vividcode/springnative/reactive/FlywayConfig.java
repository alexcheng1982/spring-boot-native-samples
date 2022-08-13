package io.vividcode.springnative.reactive;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlywayConfig {

  @Bean(initMethod = "migrate")
  Flyway flyway(Environment env) {
    String schema = env.getRequiredProperty("spring.flyway.default-schema");
    return new Flyway(
        Flyway.configure()
            .baselineOnMigrate(true)
            .dataSource(
                env.getRequiredProperty("spring.flyway.url"),
                env.getRequiredProperty("spring.flyway.user"),
                env.getRequiredProperty("spring.flyway.password"))
            .schemas(schema)
            .defaultSchema(schema));
  }
}
