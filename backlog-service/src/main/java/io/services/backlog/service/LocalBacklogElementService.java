package io.services.backlog.service;

import io.services.backlog.model.LocalBacklogElement;
import io.services.backlog.repository.BacklogElementRepository;
import io.services.backlog.repository.LocalBacklogElementRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LocalBacklogElementService
        extends AbstractBacklogElementService<LocalBacklogElement>
        implements BacklogElementService<LocalBacklogElement> {

    private final LocalBacklogElementRepository localBacklogElementRepository;

    public LocalBacklogElementService(
            LocalBacklogElementRepository localBacklogElementRepository,
            BacklogElementRepository repository) {
        super(repository);
        this.localBacklogElementRepository = localBacklogElementRepository;
    }

    @Override
    public Mono<LocalBacklogElement> save(LocalBacklogElement element) {
        return this.localBacklogElementRepository.save(element);
    }

    @Override
    public Mono<LocalBacklogElement> getById(Long id) {
        return localBacklogElementRepository.findById(id);
    }

}
