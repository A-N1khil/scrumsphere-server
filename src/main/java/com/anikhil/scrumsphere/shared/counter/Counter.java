package com.anikhil.scrumsphere.shared.counter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("counters")
@Getter
@Setter
@AllArgsConstructor
public class Counter {

	@Id
	private String id;
	private String name;
	private Long tasks;
	private Long epics;
	private Long projects;

	@Override
	public String toString() {
		return String.format("Counter{id='%s', tasks=%d, epics=%d, projects=%d}", id, tasks, epics, projects);
	}
}
