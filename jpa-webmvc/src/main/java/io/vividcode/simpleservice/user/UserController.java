package io.vividcode.simpleservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserVO> getUser(@PathVariable("userId") Long userId) {
        return this.userService.find(userId)
                .map(user -> new UserVO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getGender()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
