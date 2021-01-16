package io.services.backlog.repository;

import io.services.backlog.model.ImmutableBacklogModule;
import reactor.core.publisher.Mono;

public interface BacklogModuleRepository {

    Mono<ImmutableBacklogModule> findById(Long id);

    // Flux<ImmutableBacklogModule> modulesByUserId(Long id);

    // Flux<BacklogElement> moduleElements(Long moduleId);

    Mono<ImmutableBacklogModule> save(ImmutableBacklogModule module);

    // Mono<ImmutableBacklogModule> addModuleElement(BacklogModuleElement moduleElement);

    Mono<Void> delete(Long id);

    // Mono<Void> deleteModuleElements(Long id);
}
