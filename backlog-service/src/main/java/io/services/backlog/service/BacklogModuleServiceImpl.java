package io.services.backlog.service;

import io.services.backlog.model.BacklogModule;
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
    public Flux<BacklogModule> getByUserId(Long userId) {
        return Flux.empty(); // backlogModuleRepository.fin(userId);
    }

    @Override
    public Mono<BacklogModule> getById(Long id) {
        return backlogModuleRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return backlogModuleRepository.deleteById(id);
    }

    @Override
    public Mono<BacklogModule> save(BacklogModule module) {
        return backlogModuleRepository.save(module);
    }
}
