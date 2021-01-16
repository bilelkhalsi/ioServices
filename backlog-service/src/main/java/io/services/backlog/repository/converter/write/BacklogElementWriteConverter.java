package io.services.backlog.repository.converter.write;

import com.google.common.collect.ImmutableMap;
import io.services.backlog.model.BacklogElement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@WritingConverter
public enum BacklogElementWriteConverter implements Converter<BacklogElement, OutboundRow> {
    INSTANCE;

    @Override
    public OutboundRow convert(BacklogElement backlogElement) {
        ImmutableMap<String, Parameter> map = ImmutableMap.<String, Parameter>builder()
                .put(COLUMN_ID, Parameter.from(backlogElement.getId()))
                .put(COLUMN_TITLE, Parameter.from(backlogElement.getTitle()))
                .put(COLUMN_DESCRIPTION, Parameter.from(backlogElement.getDescription()))
                .put(COLUMN_CREATION_DATE, Parameter.from(backlogElement.getCreationDate()))
                .put(COLUMN_LEVEL, Parameter.from(backlogElement.getLevelId()))
                .put(COLUMN_TYPE, Parameter.from(backlogElement.getTypeId()))
                .put(COLUMN_USER_ID, Parameter.from(backlogElement.getUserId()))
                .put(COLUMN_METADATA, Parameter.from(backlogElement.getMetadata()))
                .build();
        return new OutboundRow(map);
    }
}
