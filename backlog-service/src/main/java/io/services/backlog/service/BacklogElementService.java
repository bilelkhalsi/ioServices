package io.services.backlog.service;

import io.services.backlog.model.BacklogElement;
import io.services.backlog.model.BaseBacklogElement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BacklogElementService<T extends BaseBacklogElement> {

    Mono<T> save(T element);

    Mono<T> getById(Long id);

    Flux<BacklogElement> getPage(int page, int size);

    Mono<Void> deleteById(long id);
}
