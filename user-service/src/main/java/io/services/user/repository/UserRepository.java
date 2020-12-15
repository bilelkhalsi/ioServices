package io.services.user.repository;

import io.services.user.model.IoUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<IoUser, Long> {

}
