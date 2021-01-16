package io.services.backlog.service;

import io.services.backlog.model.LocalBacklogElement;
import io.services.backlog.repository.LocalBacklogElementRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LocalBacklogElementService
        extends AbstractBacklogElementService<LocalBacklogElement>
        implements BacklogElementService<LocalBacklogElement> {

    private final LocalBacklogElementRepository repository;

    public LocalBacklogElementService(LocalBacklogElementRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Mono<LocalBacklogElement> save(LocalBacklogElement element) {
        return this.repository.save(element);
    }

    @Override
    public Mono<LocalBacklogElement> getById(Long id) {
        return null;
    }

}
