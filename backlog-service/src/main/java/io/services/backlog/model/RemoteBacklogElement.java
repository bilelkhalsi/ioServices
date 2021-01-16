package io.services.backlog.model;

import org.immutables.value.Value;

@Value.Immutable
@BacklogValue
public interface RemoteBacklogElement extends BaseBacklogElement {

    String getUrl();

    String getTranscription();

}