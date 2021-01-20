package io.services.backlog.repository.converter.read;

import io.r2dbc.spi.Row;
import io.services.backlog.model.BacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import static io.services.backlog.repository.converter.Constants.*;

@Component
@ReadingConverter
public class BacklogElementLevelReadConverter implements Converter<Row, BacklogElementLevel> {

    @Override
    public BacklogElementLevel convert(Row row) {
        return ImmutableBacklogElementLevel
                .builder()
                .id(row.get(COLUMN_ID, Long.class))
                .code(row.get(COLUMN_CODE, String.class))
                .description(row.get(COLUMN_DESCRIPTION, String.class))
                .build();
    }
}
