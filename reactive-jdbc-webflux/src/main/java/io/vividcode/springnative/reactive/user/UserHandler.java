package io.vividcode.springnative.reactive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

  @Autowired UserRepository userRepository;

  public Mono<ServerResponse> getUser(ServerRequest request) {
    long userId = Long.parseLong(request.pathVariable("userId"));
    return userRepository
        .findById(userId)
        .flatMap(user -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(user))
        .switchIfEmpty(notFound().build());
  }
}
