package io.services.backlog.api;

import io.services.backlog.model.ImmutableBacklogElement;
import io.services.backlog.service.BacklogElementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Tag(name = "Element", description = "Backlog element api")
@RestController
@RequestMapping("/backlog/elements")
public class BacklogElementApi {

    private final List<BacklogElementService> services;
    private final Optional<BacklogElementService> anyService;

    public BacklogElementApi(List<BacklogElementService> services) {
        this.services = (Objects.isNull(services)) ? Collections.emptyList() : services;
        this.anyService = this.services.stream().findFirst();
    }

    @GetMapping
    public Flux<ImmutableBacklogElement> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return anyService
                .map(service -> service.getPage(page, size))
                .orElseGet(Flux::<ImmutableBacklogElement>empty);
    }


    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") long id) {
        return anyService.map(service -> service.deleteById(id)).orElseGet(Mono::empty);
    }

}
