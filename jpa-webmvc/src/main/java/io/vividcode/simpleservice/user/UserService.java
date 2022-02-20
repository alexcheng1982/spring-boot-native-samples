package io.vividcode.simpleservice.user;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> find(Long userId) {
        return this.userRepository.findById(userId);
    }
}
