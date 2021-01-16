package io.services.backlog.model;


import org.immutables.value.Value;

@Value.Immutable
@BacklogValue
public interface BacklogElementLevel {

    long getId();

    String getCode();

    String getDescription();
}
