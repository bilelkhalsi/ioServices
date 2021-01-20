package io.services.backlog.model;


import org.immutables.value.Value;
import org.springframework.data.relational.core.mapping.Table;

import static io.services.backlog.repository.converter.Constants.BacklogElement.ELEMENT_TABLE_NAME;

@Table(ELEMENT_TABLE_NAME)
@Value.Immutable
@BacklogValue
public interface BacklogElementLevel {

    long getId();

    String getCode();

    String getDescription();
}
