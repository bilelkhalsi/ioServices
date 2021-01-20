package io.services.backlog.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;


@SpringBootTest
class BacklogElementRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(BacklogElementRepositoryTest.class);

    @Autowired
    BacklogElementRepository sut;


    @Test
    void should_return_one_element() {
        StepVerifier.create(sut.findById(15L))
                .verifyComplete();
    }

    @Test
    void should_return_all_elements() {
        StepVerifier.create(sut.findAll())
                .verifyComplete();
    }

}