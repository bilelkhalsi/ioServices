package io.services.backlog.repository.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import io.services.backlog.model.ImmutableBacklogElement;
import io.services.backlog.model.ImmutableLocalBacklogElement;
import io.services.backlog.model.ImmutableRemoteBacklogElement;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.BiFunction;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@Component
public class BacklogElementConverter {

    private final JsonbToMapConverter jsonbToMapConverter;
    private final MapToJsonbConverter mapToJsonbConverter;

    public BacklogElementConverter(ObjectMapper objectMapper) {
        this.jsonbToMapConverter = new JsonbToMapConverter(objectMapper);
        this.mapToJsonbConverter = new MapToJsonbConverter(objectMapper);
    }

    public Json serializeMetadata(Map<String, String> metadata) {
        return mapToJsonbConverter.convert(metadata);
    }

    public Map<String, String> mapMetadata(Json metadata) {
        return jsonbToMapConverter.convert(metadata);
    }

    public BiFunction<Row, RowMetadata, ImmutableBacklogElement> mapBacklogElement() {
        return (row, metadata) -> ImmutableBacklogElement.builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .description(row.get(COLUMN_DESCRIPTION, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .levelId(row.get(COLUMN_LEVEL, Long.class))
                .typeId(row.get(COLUMN_TYPE, Long.class))
                .metadata(mapMetadata(row.get(COLUMN_METADATA, Json.class)))
                .build();
    }

    public BiFunction<Row, RowMetadata, ImmutableLocalBacklogElement> mapLocalBacklogElement() {
        return (row, metadata) -> ImmutableLocalBacklogElement.builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .description(row.get(COLUMN_DESCRIPTION, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .levelId(row.get(COLUMN_LEVEL, Long.class))
                .typeId(row.get(COLUMN_TYPE, Long.class))
                .content(row.get(COLUMN_CONTENT, String.class))
                .metadata(mapMetadata(row.get(COLUMN_METADATA, Json.class)))
                .build();
    }


    public BiFunction<Row, RowMetadata, ImmutableRemoteBacklogElement> mapRemoteBacklogElement() {
        return (row, metadata) -> ImmutableRemoteBacklogElement.builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .description(row.get(COLUMN_DESCRIPTION, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .levelId(row.get(COLUMN_LEVEL, Long.class))
                .typeId(row.get(COLUMN_TYPE, Long.class))
                .url(row.get(COLUMN_URL, String.class))
                .transcription(row.get(COLUMN_TRANSCRIPTION, String.class))
                .metadata(mapMetadata(row.get(COLUMN_METADATA, Json.class)))
                .build();
    }
}
