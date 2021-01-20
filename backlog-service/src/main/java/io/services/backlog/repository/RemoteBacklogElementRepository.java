package io.services.backlog.repository;

import io.services.backlog.model.RemoteBacklogElement;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RemoteBacklogElementRepository extends R2dbcRepository<RemoteBacklogElement, Long> {

}