package io.services.backlog.repository;

import io.services.backlog.model.LocalBacklogElement;
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

import static io.services.backlog.repository.converter.BacklogElementConstants.COLUMN_ID;
import static io.services.backlog.repository.converter.BacklogElementConstants.ELEMENT_TABLE_NAME;
import static io.services.backlog.repository.converter.BacklogModuleConstants.COLUMN_TITLE;
import static io.services.backlog.repository.converter.BacklogModuleConstants.MODULE_TABLE_NAME;

@Repository
@Transactional(readOnly = true)
public class LocalBacklogElementRepositoryImpl
        extends AbstractBacklogElementRepository<LocalBacklogElement>
        implements LocalBacklogElementRepository {

    public LocalBacklogElementRepositoryImpl(R2dbcEntityTemplate entityTemplate) {
        super(entityTemplate);
    }

    @Override
    public Mono<LocalBacklogElement> findById(Long id) {
        return null;
    }

    @Override
    public Mono<LocalBacklogElement> findById(Publisher<Long> id) {
        return null;
    }

    @Override
    public Flux<LocalBacklogElement> findAllById(Iterable<Long> ids) {
        return null;
    }

    @Override
    public Flux<LocalBacklogElement> findAllById(Publisher<Long> idPublisher) {
        return null;
    }

    @Override
    public Flux<LocalBacklogElement> saveAll(Iterable<LocalBacklogElement> elements) {
        return null;
    }

    @Override
    public Flux<LocalBacklogElement> saveAll(Publisher<LocalBacklogElement> elementStream) {
        return null;
    }

    @Override
    @Transactional
    public Mono<LocalBacklogElement> save(LocalBacklogElement element) {

        Assert.notNull(element, "Object to save must not be null!");

        if (element.getId().isEmpty()) {
            return this.entityTemplate.insert(LocalBacklogElement.class)
                    .into(MODULE_TABLE_NAME)
                    .using(element);
        }

        return this.entityTemplate.update(LocalBacklogElement.class)
                .inTable(ELEMENT_TABLE_NAME)
                .matching(Query.query(Criteria.where(COLUMN_ID).is(element.getId().get())))
                .apply(Update.update(COLUMN_TITLE, element.getTitle()))
                .handle((rowsUpdated, sink) -> {
                    if (rowsUpdated == 0) {
                        // sink.error(); new OptimisticLockingFailureException(this.formatOptimisticLockingExceptionMessage(entity, persistentEntity)));
                        sink.error(new TransientDataAccessResourceException(
                                String.format("Failed to update table [%s]. Row with Id [%s] does not exist.", MODULE_TABLE_NAME, COLUMN_ID)));
                    } else {
                        sink.next(element);
                    }
                });

    }

}