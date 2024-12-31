package com.anikhil.scrumsphere.http;

import com.anikhil.scrumsphere.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BaseController {

	protected final JsonUtils jsonUtils;

	public BaseController(JsonUtils jsonUtils) {
		this.jsonUtils = jsonUtils;
	}

	protected JsonNode sendSuccessResponse(Object o) {
		return sendSuccessResponse(o, null);
	}

	protected JsonNode sendSuccessResponse(Object o, String message) {
		ResponseHolder responseHolder = new ResponseHolder(o, message, Status.SUCCESS);
		return this.jsonUtils.toJsonSilently(responseHolder);
	}

	protected JsonNode sendFailureResponse(Object o) {
		return sendFailureResponse(o, null);
	}

	protected JsonNode sendFailureResponse(Object o, String message) {
		ResponseHolder responseHolder = new ResponseHolder(o, message, Status.ERROR);
		return this.jsonUtils.toJsonSilently(responseHolder);
	}

	@AllArgsConstructor
	@Setter
	@Getter
	public static class ResponseHolder {

		private Object data;
		private String message;
		private Status status;
	}

	enum Status {
		SUCCESS,
		ERROR,
	}
}
