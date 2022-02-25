package io.vividcode.springnative.reactive.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
