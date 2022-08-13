package io.vividcode.springnative.jpawebmvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired UserRepository userRepository;

  public Optional<User> find(Long userId) {
    return this.userRepository.findById(userId);
  }
}
