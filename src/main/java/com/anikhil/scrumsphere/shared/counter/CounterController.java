package com.anikhil.scrumsphere.shared.counter;

import com.anikhil.scrumsphere.http.BaseController;
import com.anikhil.scrumsphere.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController extends BaseController {

	private final CounterService counterService;

	public CounterController(CounterService counterService, JsonUtils jsonUtils) {
		super(jsonUtils);
		this.counterService = counterService;
	}

	@GetMapping("/counter")
	public JsonNode getCurrentCounter() {
		return sendSuccessResponse(counterService.getAll());
	}

	@GetMapping("/counter/tasks")
	public JsonNode getTasksCounter() {
		return sendSuccessResponse(counterService.getCounterForType(CounterType.TASKS));
	}
}
