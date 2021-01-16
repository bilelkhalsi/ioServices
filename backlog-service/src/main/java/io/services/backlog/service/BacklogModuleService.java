package io.services.backlog.service;

import io.services.backlog.model.ImmutableBacklogModule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BacklogModuleService {

    Flux<ImmutableBacklogModule> getByUserId(long userId);

    Mono<ImmutableBacklogModule> getById(long id);

    Mono<Void> deleteById(long id);

    Mono<ImmutableBacklogModule> save(ImmutableBacklogModule module);
}
