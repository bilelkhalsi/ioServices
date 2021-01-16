package io.services.backlog.repository;


import io.services.backlog.model.BacklogElement;
import io.services.backlog.model.BaseBacklogElement;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BacklogElementRepository<T extends BaseBacklogElement> {

    Mono<T> save(T element);

    Flux<T> saveAll(Iterable<T> elements);

    Flux<T> saveAll(Publisher<T> elementStream);

    Mono<T> findById(Long id);

    Mono<T> findById(Publisher<Long> id);

    Mono<Boolean> existsById(Long id);

    Mono<Boolean> existsById(Publisher<Long> id);

    Flux<BacklogElement> findAll(int page, int size);

    Flux<T> findAllById(Iterable<Long> ids);

    Flux<T> findAllById(Publisher<Long> idPublisher);

    Mono<Long> count();

    Mono<Void> deleteById(Long id);

    Mono<Void> deleteById(Publisher<Long> id);

}
