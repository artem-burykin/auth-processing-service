package com.burykin.auth_api.exception;

import com.burykin.auth_api.auth.exception.AuthenticationFailedException;
import com.burykin.auth_api.exception.dto.ErrorResponse;
import com.burykin.auth_api.processing.exception.ServiceBException;
import com.burykin.auth_api.processing.exception.UnauthorizedException;
import com.burykin.auth_api.user.exception.UserAlreadyExistException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ValidationErrorExtractor extractor;

    public GlobalExceptionHandler(ValidationErrorExtractor extractor) {
        this.extractor = extractor;
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex,
                                                                     HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse response = ErrorResponse.builder(status.value(), ex.getMessage(), status.getReasonPhrase(),
                request.getRequestURI()).build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationFailedException(AuthenticationFailedException ex,
                                                                     HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ErrorResponse response = ErrorResponse.builder(status.value(), ex.getMessage(), status.getReasonPhrase(),
                request.getRequestURI()).build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ServiceBException.class)
    public ResponseEntity<ErrorResponse> handleServiceBException(ServiceBException ex,
                                                                 HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;

        ErrorResponse response = ErrorResponse.builder(status.value(), ex.getMessage(), status.getReasonPhrase(),
                request.getRequestURI()).build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex,
                                                                 HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ErrorResponse response = ErrorResponse.builder(status.value(), ex.getMessage(), status.getReasonPhrase(),
                request.getRequestURI()).build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                               HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ValidationErrorExtractor.ValidationErrors errors = extractor.extract(ex.getAllErrors());

        ErrorResponse response = ErrorResponse.builder(status.value(), ExceptionMessages.VALIDATION_FAILED,
                status.getReasonPhrase(), request.getRequestURI()).errors(errors.fieldErrors()).build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(HandlerMethodValidationException ex,
                                                                                HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ValidationErrorExtractor.ValidationErrors errors = extractor.extract(ex.getAllErrors());

        ErrorResponse response = ErrorResponse.builder(status.value(), ExceptionMessages.VALIDATION_FAILED,
                status.getReasonPhrase(), request.getRequestURI()).errors(errors.fieldErrors()).globalErrors(
                errors.globalErrors()).build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse response = ErrorResponse.builder(status.value(), ExceptionMessages.UNEXPECTED_EXCEPTION,
                status.getReasonPhrase(), request.getRequestURI()).build();

        return ResponseEntity.status(status).body(response);
    }

}
