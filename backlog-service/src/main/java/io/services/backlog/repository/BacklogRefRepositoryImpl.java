package io.services.backlog.repository;

import io.services.backlog.model.ImmutableBacklogElementLevel;
import io.services.backlog.model.ImmutableBacklogElementType;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Repository
@Transactional(readOnly = true)
public class BacklogRefRepositoryImpl implements BacklogRefRepository {


    private static final String TABLE_REF_LEVEL = "REF_LEVEL";
    private static final String TABLE_REF_BACKLOG_ELEMENT_TYPE = "REF_BACKLOG_ELEMENT_TYPE";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_DESCRIPTION = "description";

    protected final R2dbcEntityTemplate r2dbcEntityTemplate;

    public BacklogRefRepositoryImpl(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public Flux<ImmutableBacklogElementLevel> getBacklogLevels() {
        return this.r2dbcEntityTemplate.select(ImmutableBacklogElementLevel.class)
                .from(TABLE_REF_LEVEL)
                .all();

    }

    @Override
    public Flux<ImmutableBacklogElementType> getBacklogTypes() {
        return this.r2dbcEntityTemplate.select(ImmutableBacklogElementType.class)
                .from(TABLE_REF_LEVEL)
                .all();
    }
}
