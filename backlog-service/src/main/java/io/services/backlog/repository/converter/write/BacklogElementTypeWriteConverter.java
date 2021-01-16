package io.services.backlog.repository.converter.write;

import com.google.common.collect.ImmutableMap;
import io.services.backlog.model.BacklogElementType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

import static io.services.backlog.repository.converter.BacklogElementConstants.COLUMN_CODE;
import static io.services.backlog.repository.converter.BacklogElementConstants.COLUMN_ID;

@WritingConverter
public enum BacklogElementTypeWriteConverter implements Converter<BacklogElementType, OutboundRow> {
    INSTANCE;

    @Override
    public OutboundRow convert(BacklogElementType elementType) {
        ImmutableMap<String, Parameter> map = ImmutableMap.<String, Parameter>builder()
                .put(COLUMN_ID, Parameter.from(elementType.getId()))
                .put(COLUMN_CODE, Parameter.from(elementType.getCode()))
                .build();
        return new OutboundRow(map);
    }
}
