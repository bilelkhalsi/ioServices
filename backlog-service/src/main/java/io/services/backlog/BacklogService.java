package io.services.backlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class BacklogService {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(BacklogService.class, args);
    }

}
