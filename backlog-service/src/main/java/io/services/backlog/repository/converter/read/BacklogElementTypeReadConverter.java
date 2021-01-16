package io.services.backlog.repository.converter.read;

import io.r2dbc.spi.Row;
import io.services.backlog.model.BacklogElementType;
import io.services.backlog.model.ImmutableBacklogElementType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import static io.services.backlog.repository.converter.BacklogElementConstants.COLUMN_CODE;
import static io.services.backlog.repository.converter.BacklogElementConstants.COLUMN_ID;

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
