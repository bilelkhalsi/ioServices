package io.services.backlog.model;

import org.immutables.value.Value;

@Value.Immutable
@BacklogValue
public interface LocalBacklogElement extends BaseBacklogElement {

    String getContent();

}
