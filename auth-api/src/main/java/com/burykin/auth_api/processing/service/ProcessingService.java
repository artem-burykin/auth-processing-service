package com.burykin.auth_api.processing.service;

import com.burykin.auth_api.exception.ExceptionMessages;
import com.burykin.auth_api.processing.Processing;
import com.burykin.auth_api.processing.ProcessingRepository;
import com.burykin.auth_api.user.User;
import com.burykin.auth_api.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class ProcessingService {
    private final ProcessingRepository processingRepository;
    private final UserService userService;

    public ProcessingService(ProcessingRepository processingRepository, UserService userService) {
        this.processingRepository = processingRepository;
        this.userService = userService;
    }

    public Processing save(String input, String output, String userEmail) {
        User user = userService.findByEmail(userEmail).orElseThrow(
                () -> new IllegalStateException(ExceptionMessages.USER_FROM_JWT_NOT_FOUND_IN_DB));
        return processingRepository.save(new Processing(input, output, user));
    }
}
