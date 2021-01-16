package io.services.backlog.api;


import io.services.backlog.model.ImmutableLocalBacklogElement;
import io.services.backlog.model.LocalBacklogElement;
import io.services.backlog.service.BacklogElementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "LocalElement", description = "Backlog local element api")
@RestController
@RequestMapping("/backlog/elements/local")
public class LocalBacklogElementApi {

    private final BacklogElementService<LocalBacklogElement> localBacklogElementService;

    public LocalBacklogElementApi(BacklogElementService<LocalBacklogElement> localBacklogElementService) {
        this.localBacklogElementService = localBacklogElementService;
    }

    @PostMapping
    public Mono<LocalBacklogElement> createElement(@RequestBody LocalBacklogElement element) {
        return this.localBacklogElementService.save(element);
    }

    @PutMapping("/{id}")
    public Mono<LocalBacklogElement> updateElement(@PathVariable long id, @RequestBody ImmutableLocalBacklogElement element) {
        return this.localBacklogElementService.save(element.withId(id));
    }

    @GetMapping("/{id}")
    public Mono<LocalBacklogElement> getElement(@PathVariable long id) {
        return this.localBacklogElementService.getById(id);
    }

}
