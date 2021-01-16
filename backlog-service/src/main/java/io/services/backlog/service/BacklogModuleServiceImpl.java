package io.services.backlog.service;

import io.services.backlog.model.ImmutableBacklogModule;
import io.services.backlog.repository.BacklogModuleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BacklogModuleServiceImpl implements BacklogModuleService {

    private final BacklogModuleRepository backlogModuleRepository;

    public BacklogModuleServiceImpl(BacklogModuleRepository backlogModuleRepository) {
        this.backlogModuleRepository = backlogModuleRepository;
    }

    @Override
    public Flux<ImmutableBacklogModule> getByUserId(long userId) {
        return Flux.empty(); // backlogModuleRepository.fin(userId);
    }

    @Override
    public Mono<ImmutableBacklogModule> getById(long id) {
        return backlogModuleRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return backlogModuleRepository.delete(id);
    }

    @Override
    public Mono<ImmutableBacklogModule> save(ImmutableBacklogModule module) {
        return backlogModuleRepository.save(module);
    }
}
