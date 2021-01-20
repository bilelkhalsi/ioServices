package io.services.backlog.model;


import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.OptionalLong;


import static io.services.backlog.repository.converter.Constants.BacklogModule.MODULE_TABLE_NAME;


@Table(MODULE_TABLE_NAME)
@Value.Immutable
@BacklogValue
public interface BacklogModule {

    OptionalLong getId();

    String getTitle();

    long getUserId();

    @Value.Default
    default LocalDateTime getCreationDate() {
        return LocalDateTime.now();
    }

    ImmutableList<ImmutableBacklogElement> getElements();

}
