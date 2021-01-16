package io.services.backlog.repository.converter.write;

import com.google.common.collect.ImmutableMap;
import io.services.backlog.model.BacklogElementLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@WritingConverter
public enum BacklogElementLevelWriteConverter implements Converter<BacklogElementLevel, OutboundRow> {
    INSTANCE;

    @Override
    public OutboundRow convert(BacklogElementLevel level) {
        ImmutableMap<String, Parameter> map = ImmutableMap.<String, Parameter>builder()
                .put(COLUMN_ID, Parameter.from(level.getId()))
                .put(COLUMN_CODE, Parameter.from(level.getCode()))
                .put(COLUMN_DESCRIPTION, Parameter.from(level.getDescription()))
                .build();
        return new OutboundRow(map);
    }
}
