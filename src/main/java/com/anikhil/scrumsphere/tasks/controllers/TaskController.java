package com.anikhil.scrumsphere.tasks.controllers;

import com.anikhil.scrumsphere.http.BaseController;
import com.anikhil.scrumsphere.tasks.models.Task;
import com.anikhil.scrumsphere.tasks.services.TaskService;
import com.anikhil.scrumsphere.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController extends BaseController {

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService, JsonUtils jsonUtils) {
		super(jsonUtils);
		this.taskService = taskService;
	}

	@GetMapping("/task/:id")
	public JsonNode hello(@PathVariable String id) {
		return sendSuccessResponse(this.taskService.getTaskById(id));
	}

	@GetMapping("/all")
	public JsonNode getAllTasks() {
		return sendSuccessResponse(this.taskService.getAllTasks());
	}

	@PostMapping("/create")
	public JsonNode createTask(@RequestBody JsonNode payload) {
		Task task = this.jsonUtils.fromJsonSilently(payload.toString(), Task.class);
		return sendSuccessResponse(this.taskService.create(task));
	}

	@PostMapping("/update")
	public JsonNode updateTask(@RequestBody JsonNode payload) {
		Task task = this.jsonUtils.fromJsonSilently(payload.toString(), Task.class);
		return sendSuccessResponse(this.taskService.saveUpdate(task));
	}
}
