package com.fiap.tech.Gateway.utils.handleExceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains exception handlers for various types of exceptions and returns a map of error details for each
 * exception.
 */
@RestControllerAdvice
@SuppressWarnings("unused")
public class HandleErrors {
    private final Instant timestamp = Instant.now();

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        errors.put("timestamp", timestamp.toString());

        ex.printStackTrace();
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleConflict(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp.toString());
        errors.put("message", ex.getMessage());

        ex.printStackTrace();
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleNotFound(EntityNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp.toString());
        errors.put("message", ex.getMessage());

        ex.printStackTrace();
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handle(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp.toString());
        errors.put("message", "something goes wrong");

        ex.printStackTrace();
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleConflict(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp.toString());
        errors.put("message", ex.getMessage());

        ex.printStackTrace();
        return errors;
    }
}
