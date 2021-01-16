package io.services.backlog.repository.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cyclops.control.Try;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;


public class MapToJsonbConverter implements Converter<Map<String, String>, Json> {

    private final ObjectMapper objectMapper;

    public MapToJsonbConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Json convert(Map<String, String> map) {
        return Try.withCatch(() -> objectMapper.writeValueAsString(map), JsonProcessingException.class)
                .map(Json::of)
                .orElseGet(() -> Json.of("{}"));

    }
}
