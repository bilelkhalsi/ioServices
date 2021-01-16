package io.services.backlog.repository;

import io.services.backlog.model.BacklogElement;
import io.services.backlog.model.BaseBacklogElement;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;
import static org.springframework.data.relational.core.query.Criteria.where;

@Transactional(
        readOnly = true
)
public abstract class AbstractBacklogElementRepository<T extends BaseBacklogElement> implements BacklogElementRepository<T> {

    protected final R2dbcEntityTemplate entityTemplate;

    public AbstractBacklogElementRepository(R2dbcEntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    @Override
    public Mono<Long> count() {
        return this.entityTemplate.getDatabaseClient()
                .sql("SELECT COUNT(ID) FROM BACKLOG_ELEMENT")
                .map(row -> row.get(0, Long.class))
                .first();

    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        Assert.notNull(id, "Id must not be null!");
        return this.entityTemplate.select(Boolean.class)
                .from(ELEMENT_TABLE_NAME)
                .matching(
                        Query.query(where(COLUMN_ID).is(id))
                )
                .first()
                .hasElement();
    }

    @Override
    public Mono<Boolean> existsById(Publisher<Long> id) {
        return Mono.from(id).flatMap(this::findById).hasElement();
    }

    @Override
    public Flux<BacklogElement> findAll(int page, int size) {
        return entityTemplate.select(BacklogElement.class)
                .from(ELEMENT_TABLE_NAME)
                .matching(Query
                        .empty()
                        .offset((long) size * page)
                        .limit(size)
                        .sort(Sort.by(COLUMN_CREATION_DATE).descending())).all();
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(Long id) {
        Assert.notNull(id, "Id must not be null!");
        return this.entityTemplate.delete(BacklogElement.class)
                .from(ELEMENT_TABLE_NAME)
                .matching(Query.query(
                        where(COLUMN_ID).is(id)
                )).all().then();
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(Publisher<Long> id) {
        Assert.notNull(id, "The Id Publisher must not be null!");
        return Flux.from(id).buffer().filter(ids -> !ids.isEmpty()).concatMap(ids -> {
            return ids.isEmpty() ? Flux.empty() : this.entityTemplate.delete(BacklogElement.class)
                    .from(ELEMENT_TABLE_NAME)
                    .matching(Query.query(
                            where(COLUMN_ID).in(ids)
                    ))
                    .all();
        }).then();
    }

}
