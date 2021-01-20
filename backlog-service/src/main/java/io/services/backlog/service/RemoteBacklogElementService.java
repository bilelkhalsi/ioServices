package io.services.backlog.service;

import io.services.backlog.model.RemoteBacklogElement;
import io.services.backlog.repository.BacklogElementRepository;
import io.services.backlog.repository.RemoteBacklogElementRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RemoteBacklogElementService
        extends AbstractBacklogElementService<RemoteBacklogElement>
        implements BacklogElementService<RemoteBacklogElement> {

    private final RemoteBacklogElementRepository remoteBacklogElementRepository;

    public RemoteBacklogElementService(RemoteBacklogElementRepository remoteBacklogElementRepository,
                                       BacklogElementRepository repository) {
        super(repository);
        this.remoteBacklogElementRepository = remoteBacklogElementRepository;
    }

    @Override
    public Mono<RemoteBacklogElement> save(RemoteBacklogElement element) {
        return this.remoteBacklogElementRepository.save(element);
    }

    @Override
    public Mono<RemoteBacklogElement> getById(Long id) {
        return remoteBacklogElementRepository.findById(id);
    }

}
