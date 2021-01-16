package io.services.backlog.model;

import com.google.common.collect.ImmutableMap;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalLong;


/*
No @Value.Immutable
@see https://github.com/immutables/immutables/issues/330
 */
public interface BaseBacklogElement {

    Optional<Long> getId();

    String getTitle();

    String getDescription();

    Long getUserId();

    Long getLevelId();

    Long getTypeId();

    @Value.Default
    default LocalDateTime getCreationDate() {
        return LocalDateTime.now();
    }

    @Value.Default
    default ImmutableMap<String, String> getMetadata() {
        return ImmutableMap.of();
    }

}
