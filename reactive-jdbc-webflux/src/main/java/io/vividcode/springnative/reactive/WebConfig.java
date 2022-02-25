package io.vividcode.springnative.reactive;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import io.vividcode.springnative.reactive.user.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class WebConfig {

    @Bean
    RouterFunction<?> userRouter(UserHandler userHandler) {
        return route()
                .GET("/api/v1/user/{userId}", accept(MediaType.APPLICATION_JSON),
                        userHandler::getUser)
                .build();
    }
}
