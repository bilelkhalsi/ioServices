package io.services.backlog.repository.converter.write;

import com.google.common.collect.ImmutableMap;
import io.services.backlog.model.BacklogModule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Component;

import static io.services.backlog.repository.converter.Constants.*;

@Component
@WritingConverter
public class BacklogModuleWriteConverter implements Converter<BacklogModule, OutboundRow> {

    @Override
    public OutboundRow convert(BacklogModule module) {
        ImmutableMap<String, Parameter> map = ImmutableMap.<String, Parameter>builder()
                .put(COLUMN_ID, Parameter.from(module.getId()))
                .put(COLUMN_TITLE, Parameter.from(module.getTitle()))
                .put(COLUMN_CREATION_DATE, Parameter.from(module.getCreationDate()))
                .put(COLUMN_USER_ID, Parameter.from(module.getUserId()))
                .build();
        return new OutboundRow(map);
    }
}
