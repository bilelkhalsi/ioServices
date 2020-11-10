package io.service.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(builder = "new") // builder has to have constructor
@JsonDeserialize(builder = ImmutableIoUserDTO.Builder.class)
@Schema(name = "IoUser", implementation = ImmutableIoUserDTO.class)
public interface IoUserDTO {

    @Schema(description = "User id")
    Optional<Long> id();

    @Schema(description = "User email")
    String email();

    @Schema(description = "User first name")
    String firstName();

    @Schema(description = "User last name")
    String lastName();

}
