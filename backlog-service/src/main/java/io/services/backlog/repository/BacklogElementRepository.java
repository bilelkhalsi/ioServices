package io.services.backlog.repository;


import io.services.backlog.model.BacklogElement;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;


public interface BacklogElementRepository extends ReactiveSortingRepository<BacklogElement, Long> {


}
