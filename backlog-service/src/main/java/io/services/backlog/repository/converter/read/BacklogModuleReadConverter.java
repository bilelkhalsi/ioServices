package io.services.backlog.repository.converter.read;

import io.r2dbc.spi.Row;
import io.services.backlog.model.BacklogModule;
import io.services.backlog.model.ImmutableBacklogModule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static io.services.backlog.repository.converter.Constants.*;

@Component
@ReadingConverter
public class BacklogModuleReadConverter implements Converter<Row, BacklogModule> {
    @Override
    public BacklogModule convert(Row row) {
        return ImmutableBacklogModule
                .builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .userId(row.get(COLUMN_USER_ID, Long.class))
                .build();
    }
}
