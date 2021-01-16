package io.services.backlog.service;

import io.services.backlog.model.RemoteBacklogElement;
import io.services.backlog.repository.RemoteBacklogElementRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RemoteBacklogElementService
        extends AbstractBacklogElementService<RemoteBacklogElement>
        implements BacklogElementService<RemoteBacklogElement> {

    private final RemoteBacklogElementRepository repository;

    public RemoteBacklogElementService(RemoteBacklogElementRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Mono<RemoteBacklogElement> save(RemoteBacklogElement element) {
        return this.repository.save(element);
    }

    @Override
    public Mono<RemoteBacklogElement> getById(Long id) {
        return repository.findById(id);
    }

}
