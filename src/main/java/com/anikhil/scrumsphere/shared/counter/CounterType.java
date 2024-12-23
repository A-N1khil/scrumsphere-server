package com.anikhil.scrumsphere.shared.counter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CounterType {

	TASKS("tasks"),
	EPICS("epics"),
	PROJECTS("projects");

	private final String type;
}
