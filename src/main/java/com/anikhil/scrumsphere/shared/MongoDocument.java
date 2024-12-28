package com.anikhil.scrumsphere.shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class MongoDocument {

	@Id
	public String id;
}
