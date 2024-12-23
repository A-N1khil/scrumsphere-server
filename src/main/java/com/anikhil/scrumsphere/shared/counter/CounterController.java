package com.anikhil.scrumsphere.shared.counter;

import com.anikhil.scrumsphere.http.BaseController;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CounterController extends BaseController {

	private final CounterService counterService;

	@GetMapping("/counter")
	public JsonNode getCurrentCounter() {
		return sendSuccessResponse(counterService.getAll());
	}

	@GetMapping("/counter/tasks")
	public JsonNode getTasksCounter() {
		return sendSuccessResponse(counterService.getCounterForType(CounterType.TASKS));
	}
}
