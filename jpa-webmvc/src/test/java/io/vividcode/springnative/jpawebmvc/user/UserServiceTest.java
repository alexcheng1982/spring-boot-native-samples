package io.vividcode.springnative.jpawebmvc.user;

import com.playtika.test.common.spring.DockerPresenceBootstrapConfiguration;
import com.playtika.test.postgresql.EmbeddedPostgreSQLBootstrapConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ImportAutoConfiguration(
    classes = {
      DockerPresenceBootstrapConfiguration.class,
      EmbeddedPostgreSQLBootstrapConfiguration.class,
    })
@ActiveProfiles("test")
@DisplayName("User service")
class UserServiceTest {

  @Autowired UserService userService;

  @Test
  @DisplayName("find user")
  void testFindUser() {
    assertThat(userService.find(1L)).isNotNull();
  }
}
