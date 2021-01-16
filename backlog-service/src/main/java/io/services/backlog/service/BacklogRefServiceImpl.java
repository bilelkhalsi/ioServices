package io.services.backlog.service;

import io.services.backlog.model.ImmutableBacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementType;
import io.services.backlog.repository.BacklogRefRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class BacklogRefServiceImpl implements BacklogRefService {

    private final BacklogRefRepository backlogRefRepository;

    BacklogRefServiceImpl(BacklogRefRepository backlogRefRepository) {
        this.backlogRefRepository = backlogRefRepository;
    }

    @Override
    public Flux<ImmutableBacklogElementLevel> getBacklogLevels() {
        return backlogRefRepository.getBacklogLevels();
    }

    @Override
    public Flux<ImmutableBacklogElementType> getBacklogTypes() {
        return backlogRefRepository.getBacklogTypes();
    }
}
