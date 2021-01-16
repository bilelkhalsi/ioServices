package io.services.backlog.repository.converter;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import io.services.backlog.model.BacklogModule;
import io.services.backlog.model.ImmutableBacklogModule;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@Component
public class BacklogModuleConverter implements BiFunction<Row, RowMetadata, BacklogModule> {


    @Override
    public ImmutableBacklogModule apply(Row row, RowMetadata metadata) {
        return ImmutableBacklogModule.builder()
                .id(row.get(COLUMN_ID, Long.class))
                .title(row.get(COLUMN_TITLE, String.class))
                .creationDate(row.get(COLUMN_CREATION_DATE, LocalDateTime.class))
                .build();
    }

    public ImmutableBacklogModule apply(Row row, String ColumnPrefix) {
        return ImmutableBacklogModule.builder()
                .id(row.get(ColumnPrefix + COLUMN_ID, Long.class))
                .title(row.get(ColumnPrefix + COLUMN_TITLE, String.class))
                .creationDate(row.get(ColumnPrefix + COLUMN_CREATION_DATE, LocalDateTime.class))
                .build();
    }
}
