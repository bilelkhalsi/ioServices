package io.services.backlog.repository;


import io.services.backlog.model.ImmutableBacklogModule;
import io.services.backlog.repository.converter.BacklogElementConverter;
import io.services.backlog.repository.converter.BacklogModuleConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.services.backlog.repository.converter.BacklogElementConstants.COLUMN_ID;
import static io.services.backlog.repository.converter.BacklogModuleConstants.*;
import static org.springframework.data.relational.core.query.Criteria.where;

@Repository
@Transactional(readOnly = true)
public class BacklogModuleRepositoryImpl implements BacklogModuleRepository {
    private final R2dbcEntityTemplate entityTemplate;

    private final String modulesByUserId;
    private final String elementsByModuleId;

    public BacklogModuleRepositoryImpl(R2dbcEntityTemplate entityTemplate,
                                       @Value("${backlog.modulesByUserId}") String modulesByUserId,
                                       @Value("${backlog.elementsByModuleId}") String elementsByModuleId) {
        this.entityTemplate = entityTemplate;
        this.modulesByUserId = modulesByUserId;
        this.elementsByModuleId = elementsByModuleId;
    }


    public Mono<ImmutableBacklogModule> findById(Long id) {
        return this.entityTemplate
                .select(ImmutableBacklogModule.class)
                .from(MODULE_TABLE_NAME)
                .matching(Query.query(
                        where(COLUMN_ID).is(id)
                )).one();
    }

    public Flux<ImmutableBacklogModule> findByUserId(Long userId) {
        return this.entityTemplate
                .select(ImmutableBacklogModule.class)
                .matching(Query.query(
                        where(COLUMN_USER_ID).is(userId)
                ))
                .all();

/*
        return this.databaseClient.execute(modulesByUserId).bind(COLUMN_USER_ID, userId)
                .map(row -> row)
                .all().groupBy(row -> row.get("bm_id"))
                .flatMap(group -> group.collect(ImmutableBacklogModule.Builder::new, (acc, row) -> acc
                                .id(row.get("bm_id", Long.class))
                                .userId(row.get("bm_user_id", Long.class))
                                .title(row.get("bm_title", String.class))
                                .creationDate(row.get("bm_creation_date", LocalDateTime.class))
                        // .addElements(elementMapper.apply(row, "be_"))
                ).map(ImmutableBacklogModule.Builder::build));

 */
    }


    @Transactional
    public Mono<ImmutableBacklogModule> save(ImmutableBacklogModule module) {
        Assert.notNull(module, "Object to save must not be null!");
        if (module.getId().isEmpty()) {
            return this.entityTemplate.insert(ImmutableBacklogModule.class)
                    .into(MODULE_TABLE_NAME)
                    .using(module);
            /*
                    .into(MODULE_TABLE_NAME)
                    .value(COLUMN_TITLE, module.getTitle())
                    .value(COLUMN_CREATION_DATE, module.getCreationDate())
                    .value(COLUMN_USER_ID, module.getUserId())
                    .map((row, rowMetadata) -> module.withId(row.get(COLUMN_ID, Long.class)))
                    .first().cache();

             */
        } else {
            return this.entityTemplate.update(ImmutableBacklogModule.class)
                    .inTable(MODULE_TABLE_NAME)
                    .matching(Query.query(where(COLUMN_ID).is(module.getId().getAsLong())))
                    .apply(Update.update(COLUMN_TITLE, module.getTitle()))
                    .handle((rowsUpdated, sink) -> {
                        if (rowsUpdated == 0) {
                            // sink.error(); new OptimisticLockingFailureException(this.formatOptimisticLockingExceptionMessage(entity, persistentEntity)));
                            sink.error(new TransientDataAccessResourceException(
                                    String.format("Failed to update table [%s]. Row with Id [%s] does not exist.", MODULE_TABLE_NAME, COLUMN_ID)));
                        } else {
                            sink.next(module);
                        }
                    });
        }
    }

    /*
            @Transactional()
            public Mono<ImmutableBacklogModule> addModuleElement (BacklogModuleElement moduleElement){
                this.entityTemplate.insert()
                        .into(MODULE_ELEMENT_TABLE_NAME)
                        .value(COLUMN_MODULE_ID, moduleElement.getModuleId())
                        .value(COLUMN_ELEMENT_ID, moduleElement.getElementId())
                        .value(COLUMN_ELEMENT_ORDER, moduleElement.getElementOrder())
                        .map((row, rowMetadata) -> new ImmutableBacklogModuleElement.Builder()
                                .moduleId(row.get(COLUMN_MODULE_ID, Long.class))
                                .elementId(row.get(COLUMN_ELEMENT_ID, Long.class))
                                .elementOrder(row.get(COLUMN_ELEMENT_ORDER, Integer.class))
                                .build())
                        .first();
                return null;

            }
*/

    @Transactional
    public Mono<Void> delete(Long id) {
        return this.entityTemplate
                .delete(ImmutableBacklogModule.class)
                .from(MODULE_TABLE_NAME)
                .matching(Query.query(
                        where(COLUMN_ID).is(id)
                )).all().then();
    }

            /*
            @Transactional
            public Mono<Void> deleteModuleElements (Long id){
                return this.databaseClient
                        .delete()
                        .from(MODULE_ELEMENT_TABLE_NAME)
                        .matching(where(COLUMN_MODULE_ID).is(id))
                        .then();
            }

             */


}
