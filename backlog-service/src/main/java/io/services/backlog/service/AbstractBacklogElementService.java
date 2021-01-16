package io.services.backlog.service;

import io.services.backlog.model.BacklogElement;
import io.services.backlog.model.BaseBacklogElement;
import io.services.backlog.model.ImmutableBacklogElement;
import io.services.backlog.repository.BacklogElementRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractBacklogElementService<T extends BaseBacklogElement> implements BacklogElementService<T> {

    private final BacklogElementRepository<T> repository;

    public AbstractBacklogElementService(BacklogElementRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Flux<BacklogElement> getPage(int page, int size) {
        return repository.findAll(page, size);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return repository.deleteById(id);
    }
}
