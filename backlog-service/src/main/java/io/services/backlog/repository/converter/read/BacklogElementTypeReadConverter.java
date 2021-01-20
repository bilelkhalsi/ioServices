package io.services.backlog.repository.converter.read;

import io.r2dbc.spi.Row;
import io.services.backlog.model.BacklogElementType;
import io.services.backlog.model.ImmutableBacklogElementType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import static io.services.backlog.repository.converter.Constants.COLUMN_CODE;
import static io.services.backlog.repository.converter.Constants.COLUMN_ID;

@Component
@ReadingConverter
public class BacklogElementTypeReadConverter implements Converter<Row, BacklogElementType> {

    @Override
    public BacklogElementType convert(Row row) {
        return ImmutableBacklogElementType
                .builder()
                .id(row.get(COLUMN_ID, Long.class))
                .code(row.get(COLUMN_CODE, String.class))
                .build();
    }
}
