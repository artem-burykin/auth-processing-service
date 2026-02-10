package com.burykin.auth_api.auth;

import com.burykin.auth_api.auth.dto.LoginUser;
import com.burykin.auth_api.auth.dto.RegisterUser;
import com.burykin.auth_api.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthorizationController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    public AuthorizationController(UserService userService, AuthorizationService authorizationService) {
        this.userService = userService;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUser registerUser) {
        userService.register(registerUser.email(), registerUser.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login (@Valid @RequestBody LoginUser loginUser) {
        String token = authorizationService.login(loginUser.email(), loginUser.password());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
