package io.services.user.service;

import io.services.user.dto.IoUserDTO;
import io.services.user.service.mapping.UserMapper;
import io.services.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<IoUserDTO> findAll() {
        return userRepository.findAll()
                .map(UserMapper::convert);

    }
}
