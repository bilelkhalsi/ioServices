package io.services.backlog.repository.converter;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import cyclops.control.Try;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;


public class JsonbToMapConverter implements Converter<Json, ImmutableMap> {

    private final ObjectMapper objectMapper;

    public JsonbToMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ImmutableMap convert(Json json) {
        return Try.withCatch(() -> objectMapper
                .readValue(json.asString(), ImmutableMap.class), JsonMappingException.class)
                .orElseGet(ImmutableMap::of);

    }
}
