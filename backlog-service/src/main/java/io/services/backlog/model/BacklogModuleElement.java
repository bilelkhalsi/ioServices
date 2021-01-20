package io.services.backlog.model;


import org.immutables.value.Value;
import org.springframework.data.relational.core.mapping.Table;

import static io.services.backlog.repository.converter.Constants.BacklogModule.MODULE_TABLE_NAME;

@Table(MODULE_TABLE_NAME)
@Value.Immutable
@BacklogValue
public interface BacklogModuleElement {

    long getModuleId();

    long getElementId();

    int getElementOrder();

}
