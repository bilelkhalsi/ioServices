package io.services.backlog.api;


import io.services.backlog.model.BacklogModule;
import io.services.backlog.model.ImmutableBacklogModule;
import io.services.backlog.service.BacklogModuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Module", description = "Backlog element module api")
@RestController
@RequestMapping("/backlog/modules")
public class BacklogModuleApi {

    private final BacklogModuleService moduleService;

    public BacklogModuleApi(BacklogModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    public Flux<ImmutableBacklogModule> currentUserModules() {
        return moduleService.getByUserId(1L);
    }

    @GetMapping("/{id}")
    public Mono<ImmutableBacklogModule> findById(@PathVariable long id) {
        return moduleService.getById(id);
    }


    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable long id) {
        return moduleService.deleteById(id);
    }


    @PostMapping
    public Mono<ImmutableBacklogModule> createModule(@RequestBody ImmutableBacklogModule module) {
        return moduleService.save(module);
    }

    @PutMapping("/{id}")
    public Mono<ImmutableBacklogModule> updateModule(@PathVariable long id, @RequestBody ImmutableBacklogModule module) {
        return moduleService.save(module.withId(id));
    }

}
