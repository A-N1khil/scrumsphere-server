package com.anikhil.scrumsphere.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("users")
public class User {

    @Id
    private String id;

    private final String userId;

    @JsonIgnore
    private final String password;

    @JsonIgnore
    private String jwtToken;

    private String name;

    private String email;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.jwtToken = DigestUtils.md5Hex(userId + password);
    }
}
