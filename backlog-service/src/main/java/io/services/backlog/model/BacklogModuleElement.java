package io.services.backlog.model;


import org.immutables.value.Value;
import org.springframework.data.relational.core.mapping.Table;

@Table("backlog_element")
@Value.Immutable
@BacklogValue
public interface BacklogModuleElement {

    long getModuleId();

    long getElementId();

    int getElementOrder();

}
