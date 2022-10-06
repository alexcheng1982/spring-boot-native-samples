package io.vividcode.springnative.reactive.user;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(@Id long id,
                   String username,
                   @Column("first_name") String firstName,
                   @Column("last_name") String lastName,
                   String email,
                   String gender) {

}
