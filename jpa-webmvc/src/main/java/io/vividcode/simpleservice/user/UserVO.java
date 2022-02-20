package io.vividcode.simpleservice.user;

import lombok.Value;

@Value
public class UserVO {

    long id;
    String username;
    String firstName;
    String lastName;
    String email;
    String gender;
}
