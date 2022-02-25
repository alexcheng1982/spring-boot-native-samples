package io.vividcode.springnative.reactive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Mono<User> find(Long userId) {
        return this.userRepository.findById(userId);
    }
}
