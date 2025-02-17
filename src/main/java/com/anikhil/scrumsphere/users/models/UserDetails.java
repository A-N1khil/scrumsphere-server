package com.anikhil.scrumsphere.users.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("userDetails")
public class UserDetails {

	private String id;
	private String userId;
	private Address address;
	private String phone;
}
