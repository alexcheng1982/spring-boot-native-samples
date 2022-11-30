package io.vividcode.springnative.jpawebmvc.user;

public record UserVO(long id, String username, String firstName, String lastName, String email,
                     String gender) {

}
