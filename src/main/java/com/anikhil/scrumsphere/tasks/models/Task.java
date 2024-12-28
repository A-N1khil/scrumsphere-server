package com.anikhil.scrumsphere.tasks.models;

import com.anikhil.scrumsphere.messages.Message;
import com.anikhil.scrumsphere.shared.MongoDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "tasks")
public class Task extends MongoDocument {

	private String taskId;
	private String projectId;
	private String title;
	private String description;
	private Status status;
	private String assignee;
	private String reporter;
	private LocalDate dueDate;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	private List<Message> messages;
}
