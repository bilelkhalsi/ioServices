package io.services.user.service;

import io.services.user.dto.IoUserDTO;
import reactor.core.publisher.Flux;

public interface UserService {

    Flux<IoUserDTO> findAll();

}
