package com.burykin.auth_api.auth;

import com.burykin.auth_api.auth.exception.AuthenticationFailedException;
import com.burykin.auth_api.user.User;
import com.burykin.auth_api.user.UserRepository;
import com.burykin.auth_api.secutiry.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthorizationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(AuthenticationFailedException::new);

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new AuthenticationFailedException();
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
