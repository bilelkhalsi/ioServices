package io.services.backlog.repository;

import io.services.backlog.model.BacklogModule;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BacklogModuleRepository extends R2dbcRepository<BacklogModule, Long> {

}
