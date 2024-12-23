package com.anikhil.scrumsphere.users.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String address1;
	private String address2;
	private String city;
	private String country;
	private String zipCode;
}
