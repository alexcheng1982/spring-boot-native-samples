package io.vividcode.springnative.reactive.user;

import com.playtika.test.common.spring.DockerPresenceBootstrapConfiguration;
import com.playtika.test.postgresql.EmbeddedPostgreSQLBootstrapConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ImportAutoConfiguration(
    classes = {
      DockerPresenceBootstrapConfiguration.class,
      EmbeddedPostgreSQLBootstrapConfiguration.class,
    })
@ActiveProfiles("test")
@DisplayName("User router")
public class UserRouterTest {

  @Autowired WebTestClient webClient;

  @Test
  @DisplayName("Find user")
  void testFindUser() {
    this.webClient
        .get()
        .uri("/api/v1/user/{id}", 1)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();
  }
}
