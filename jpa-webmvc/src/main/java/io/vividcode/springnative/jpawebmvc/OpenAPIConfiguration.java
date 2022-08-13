package io.vividcode.springnative.jpawebmvc;

import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi ->
        openApi
            .getInfo()
            .version("v1")
            .title("User API")
            .description("User API")
            .license(
                new License()
                    .name("MIT")
                    .url(
                        "https://github.com/alexcheng1982/spring-native-samples/blob/main/LICENSE"));
  }
}
