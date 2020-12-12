package io.services.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Optional;


public interface IoUserDTO {

    Optional<Long> id();

    String email();

    String firstName();

    String lastName();

}
