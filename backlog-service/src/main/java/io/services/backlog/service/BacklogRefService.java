package io.services.backlog.service;

import io.services.backlog.model.ImmutableBacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementType;
import reactor.core.publisher.Flux;

public interface BacklogRefService {

    /**
     * Return available backlog element levels
     *
     * @return levels
     */
    Flux<ImmutableBacklogElementLevel> getBacklogLevels();


    /**
     * Return available backlog element types
     *
     * @return types
     */
    Flux<ImmutableBacklogElementType> getBacklogTypes();
}
