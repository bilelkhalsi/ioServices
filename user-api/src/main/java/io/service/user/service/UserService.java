package io.service.user.service;

import io.service.user.dto.IoUserDTO;
import reactor.core.publisher.Flux;

public interface UserService {

    Flux<IoUserDTO> findAll();

}
