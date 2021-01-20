package io.services.backlog.service;

import io.services.backlog.model.BacklogModule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BacklogModuleService {

    Flux<BacklogModule> getByUserId(Long userId);

    Mono<BacklogModule> getById(Long id);

    Mono<Void> deleteById(Long id);

    Mono<BacklogModule> save(BacklogModule module);
}
