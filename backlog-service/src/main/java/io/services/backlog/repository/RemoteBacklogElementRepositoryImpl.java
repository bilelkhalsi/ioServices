package io.services.backlog.repository;

import io.services.backlog.model.RemoteBacklogElement;
import org.reactivestreams.Publisher;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.services.backlog.repository.converter.BacklogElementConstants.*;

@Repository
@Transactional(readOnly = true)
public class RemoteBacklogElementRepositoryImpl
        extends AbstractBacklogElementRepository<RemoteBacklogElement>
        implements RemoteBacklogElementRepository {

    public RemoteBacklogElementRepositoryImpl(R2dbcEntityTemplate entityTemplate) {
        super(entityTemplate);
    }

    @Override
    public Mono<RemoteBacklogElement> findById(Long id) {
        return null;
    }

    @Override
    public Mono<RemoteBacklogElement> findById(Publisher<Long> id) {
        return null;
    }

    @Override
    public Flux<RemoteBacklogElement> findAllById(Iterable<Long> ids) {
        return null;
    }

    @Override
    public Flux<RemoteBacklogElement> findAllById(Publisher<Long> idPublisher) {
        return null;
    }

    @Override
    @Transactional
    public Mono<RemoteBacklogElement> save(RemoteBacklogElement element) {

        Assert.notNull(element, "Object to save must not be null!");

        if (element.getId().isEmpty()) {
            return this.entityTemplate.insert(RemoteBacklogElement.class)
                    .into(ELEMENT_TABLE_NAME)
                    .using(element);
        }

        return this.entityTemplate.update(RemoteBacklogElement.class)
                .inTable(ELEMENT_TABLE_NAME)
                .matching(Query.query(Criteria.where(COLUMN_ID).is(element.getId().get())))
                .apply(Update.update(COLUMN_TITLE, element.getTitle()))
                .handle((rowsUpdated, sink) -> {
                    if (rowsUpdated == 0) {
                        // sink.error(); new OptimisticLockingFailureException(this.formatOptimisticLockingExceptionMessage(entity, persistentEntity)));
                        sink.error(new TransientDataAccessResourceException(
                                String.format("Failed to update table [%s]. Row with Id [%s] does not exist.", ELEMENT_TABLE_NAME, COLUMN_ID)));
                    } else {
                        sink.next(element);
                    }
                });

    }

    @Override
    @Transactional
    public Flux<RemoteBacklogElement> saveAll(Iterable<RemoteBacklogElement> elements) {
        return null;
    }

    @Override
    @Transactional
    public Flux<RemoteBacklogElement> saveAll(Publisher<RemoteBacklogElement> elementStream) {
        return null;
    }

}
