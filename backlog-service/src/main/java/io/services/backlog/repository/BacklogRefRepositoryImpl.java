package io.services.backlog.repository;

import io.services.backlog.model.ImmutableBacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementType;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import static io.services.backlog.repository.converter.Constants.BacklogElementLevel.ELEMENT_LEVEL_TABLE_NAME;
import static io.services.backlog.repository.converter.Constants.BacklogElementType.ELEMENT_TYPE_TABLE_NAME;

@Repository
@Transactional(readOnly = true)
public class BacklogRefRepositoryImpl implements BacklogRefRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public BacklogRefRepositoryImpl(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public Flux<ImmutableBacklogElementLevel> getBacklogLevels() {
        return this.r2dbcEntityTemplate.select(ImmutableBacklogElementLevel.class)
                .from(ELEMENT_LEVEL_TABLE_NAME)
                .all();
    }

    @Override
    public Flux<ImmutableBacklogElementType> getBacklogTypes() {
        return this.r2dbcEntityTemplate.select(ImmutableBacklogElementType.class)
                .from(ELEMENT_TYPE_TABLE_NAME)
                .all();
    }
}
