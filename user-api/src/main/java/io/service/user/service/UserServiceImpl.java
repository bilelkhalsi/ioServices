package io.service.user.service;

import io.service.user.dto.IoUserDTO;
import io.service.user.service.mapping.UserMapper;
import io.service.user.repository.UserRepository;
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
