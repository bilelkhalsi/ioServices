package io.services.user.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Optional;


public interface IoUser {

    Optional<Long> id();

    String email();

    String firstName();

    String lastName();

}
