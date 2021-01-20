package io.services.backlog.repository;

import io.services.backlog.model.LocalBacklogElement;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface LocalBacklogElementRepository extends R2dbcRepository<LocalBacklogElement, Long> {

}