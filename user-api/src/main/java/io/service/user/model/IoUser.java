package io.service.user.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(builder = "new") // builder has to have constructor
@JsonDeserialize(builder = ImmutableIoUser.Builder.class)
public interface IoUser {

    Optional<Long> id();

    String email();

    String firstName();

    String lastName();

}
