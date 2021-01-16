package io.services.backlog.model;


import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.OptionalLong;


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
