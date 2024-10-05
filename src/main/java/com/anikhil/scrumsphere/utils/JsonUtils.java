package com.anikhil.scrumsphere.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode toJson(Object o) {
        return MAPPER.valueToTree(o);
    }

    public static JsonNode toJsonSilently(Object o) {
        try {
            return toJson(o);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return MAPPER.readValue(json, clazz);
    }

    public static <T> T fromJsonSilently(String json, Class<T> clazz) {
        try {
            return fromJson(json, clazz);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }
}
