package com.anikhil.scrumsphere.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class JsonUtils {

	private final ObjectMapper mapper;

	public JsonNode toJson(Object o) {
		return mapper.valueToTree(o);
	}

	public JsonNode toJsonSilently(Object o) {
		try {
			return toJson(o);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
		return this.mapper.readValue(json, clazz);
	}

	public <T> T fromJsonSilently(String json, Class<T> clazz) {
		try {
			return fromJson(json, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

}
