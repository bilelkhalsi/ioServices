package io.services.backlog.api;

import io.services.backlog.model.ImmutableBacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementType;
import io.services.backlog.service.BacklogRefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "Ref", description = "Backlog static data api")
@RestController
@RequestMapping("/backlog/ref")
public class BacklogRefApi {

    private final BacklogRefService backlogRefService;

    public BacklogRefApi(BacklogRefService backlogRefService) {
        this.backlogRefService = backlogRefService;
    }

    @Operation(summary = "levels", description = "Backlog available levels")
    @GetMapping("/levels")
    public Flux<ImmutableBacklogElementLevel> getBacklogLevels() {
        return backlogRefService.getBacklogLevels();
    }


    @Operation(summary = "types", description = "Backlog available element types")
    @GetMapping("/types")
    public Flux<ImmutableBacklogElementType> getBacklogTypes() {
        return backlogRefService.getBacklogTypes();
    }
}
