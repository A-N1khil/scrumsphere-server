package com.anikhil.scrumsphere.projects.models;

import com.anikhil.scrumsphere.shared.MongoDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "projects")
public class Project extends MongoDocument {

	private String projectId;
	private String epicId;
	private String projectName;
	private String projectDescription;
}
