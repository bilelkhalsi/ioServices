package io.services.backlog.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.data.relational.core.mapping.Table;

import static io.services.backlog.repository.converter.Constants.BacklogElement.ELEMENT_TABLE_NAME;

@Table(ELEMENT_TABLE_NAME)
@Value.Immutable
@Value.Style(builder = "new") // builder has to have constructor
@JsonDeserialize(builder = ImmutableBacklogElement.Builder.class)
public interface BacklogElement extends BaseBacklogElement {


}
