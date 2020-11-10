package io.service.user.rest;

import io.service.user.dto.IoUserDTO;
import io.service.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/connected")
    public Mono<UserDetails> connectedUser(@AuthenticationPrincipal Mono<UserDetails> userDetails) {
        return userDetails;
    }

    @GetMapping
    public Flux<IoUserDTO> findAll() {
        return userService.findAll();
    }
}
