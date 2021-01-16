package io.services.backlog.api;

import io.services.backlog.model.ImmutableRemoteBacklogElement;
import io.services.backlog.model.RemoteBacklogElement;
import io.services.backlog.service.BacklogElementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "RemoteElement", description = "Backlog remote element api")
@RestController
@RequestMapping("/backlog/elements/remote")
public class RemoteBacklogElementApi {

    private final BacklogElementService<RemoteBacklogElement> remoteBacklogElementService;


    public RemoteBacklogElementApi(BacklogElementService<RemoteBacklogElement> remoteBacklogElementService) {
        this.remoteBacklogElementService = remoteBacklogElementService;
    }

    @PostMapping
    public Mono<RemoteBacklogElement> createElement(@RequestBody RemoteBacklogElement element) {
        return remoteBacklogElementService.save(element);
    }

    @PutMapping("/{id}")
    public Mono<RemoteBacklogElement> updateElement(@PathVariable long id, @RequestBody ImmutableRemoteBacklogElement element) {
        return remoteBacklogElementService.save(element.withId(id));
    }

    @GetMapping("/{id}")
    public Mono<RemoteBacklogElement> getElement(@PathVariable long id) {
        return remoteBacklogElementService.getById(id);
    }
}
