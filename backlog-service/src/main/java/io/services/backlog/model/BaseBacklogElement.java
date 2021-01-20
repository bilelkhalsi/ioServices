package io.services.backlog.model;

import com.google.common.collect.ImmutableMap;
import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

import static io.services.backlog.repository.converter.Constants.BacklogElement.COLUMN_LEVEL;
import static io.services.backlog.repository.converter.Constants.BacklogElement.COLUMN_TYPE;

/*
No @Value.Immutable
@see https://github.com/immutables/immutables/issues/330
 */
public interface BaseBacklogElement {

    @Id
    @Value.Default
    default Long getId() {
        return null;
    }

    String getTitle();

    String getDescription();

    Long getUserId();

    @Column(COLUMN_LEVEL)
    Long getLevelId();

    @Column(COLUMN_TYPE)
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
