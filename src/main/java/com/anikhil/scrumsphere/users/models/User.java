package com.anikhil.scrumsphere.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document("users")
public class User {

	@Id
	@JsonIgnore
	private String id;

	private String userId;

	private String password;

	private String jwtToken;

	private String name;

	private String email;

	private String role;

	private String gender;

	private List<String> teams;
}
