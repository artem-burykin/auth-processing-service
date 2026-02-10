package com.burykin.auth_api.user;

import com.burykin.auth_api.user.exception.UserAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return  userRepository.findByEmail(email);
    }

    public User register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistException(email);
        }

        String hash = passwordEncoder.encode(password);
        return userRepository.save(new User(email, hash));
    }
}
