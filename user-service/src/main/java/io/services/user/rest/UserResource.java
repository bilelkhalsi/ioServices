package io.services.user.rest;


import io.services.autoconfigure.ImmutableUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserResource {


    @GetMapping("/connected")
    public Mono<ImmutableUserDetails> connected(Authentication jwt) {
        return Mono.just(jwt.getDetails()).cast(ImmutableUserDetails.class);
    }

}
