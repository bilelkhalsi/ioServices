package io.services.backlog.repository.converter.read;

import com.google.common.collect.ImmutableMap;
import io.r2dbc.spi.Row;
import io.services.backlog.model.BacklogElement;
import io.services.backlog.model.ImmutableBacklogElement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@ReadingConverter
public class BacklogElementReadConverter implements Converter<Row, BacklogElement> {
    @Override
    public BacklogElement convert(Row row) {
        return ImmutableBacklogElement
                .builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .description(row.get(COLUMN_DESCRIPTION, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .typeId(row.get(COLUMN_TYPE, Long.class))
                .levelId(row.get(COLUMN_LEVEL, Long.class))
                .userId(row.get(COLUMN_USER_ID, Long.class))
                .metadata(row.get(COLUMN_METADATA, ImmutableMap.class))
                .build();
    }
}
