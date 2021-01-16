package io.services.backlog.repository;

import io.services.backlog.model.ImmutableBacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementType;
import reactor.core.publisher.Flux;

public interface BacklogRefRepository {

    Flux<ImmutableBacklogElementLevel> getBacklogLevels();

    Flux<ImmutableBacklogElementType> getBacklogTypes();

}
