package io.services.backlog.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class BacklogRefRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(BacklogRefRepositoryTest.class);

    @Autowired
    private BacklogRefRepository sut;


    @Test
    void should_return_available_backlog_element_types() {
        StepVerifier.create(sut.getBacklogTypes())
                .expectNextCount(4)
                .verifyComplete();
    }

}