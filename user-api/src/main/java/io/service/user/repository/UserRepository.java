package io.service.user.repository;

import io.service.user.model.IoUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<IoUser, Long> {

}
