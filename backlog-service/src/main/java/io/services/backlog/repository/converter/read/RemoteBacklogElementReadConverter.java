package io.services.backlog.repository.converter.read;

import com.google.common.collect.ImmutableMap;
import io.r2dbc.spi.Row;
import io.services.backlog.model.ImmutableRemoteBacklogElement;
import io.services.backlog.model.RemoteBacklogElement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@ReadingConverter
public class RemoteBacklogElementReadConverter implements Converter<Row, RemoteBacklogElement> {
    @Override
    public RemoteBacklogElement convert(Row row) {
        return ImmutableRemoteBacklogElement
                .builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .description(row.get(COLUMN_DESCRIPTION, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .typeId(row.get(COLUMN_TYPE, Long.class))
                .levelId(row.get(COLUMN_LEVEL, Long.class))
                .userId(row.get(COLUMN_USER_ID, Long.class))
                .url(row.get(COLUMN_URL, String.class))
                .transcription(row.get(COLUMN_TRANSCRIPTION, String.class))
                .metadata(row.get(COLUMN_METADATA, ImmutableMap.class))
                .build();
    }
}
