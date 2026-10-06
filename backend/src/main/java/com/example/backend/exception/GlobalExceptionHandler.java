package com.example.backend.exception;

import org.hibernate.mapping.Array;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler (DuplicateSkuException.class)
    public ResponseEntity<String> handleDuplicateSku(DuplicateSkuException exception) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(exception.getMessage());
    }

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }

    @ExceptionHandler (UnavailableQuantityException.class)
    public ResponseEntity<String> handleUnavailableQuantity(UnavailableQuantityException exception) {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_CONTENT)
            .body(exception.getMessage());
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        ApiError error = new ApiError(
            400,
            "Invalid request body",
            null
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArguementNotValid(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors
                .computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
                .add(fieldError.getDefaultMessage());
        }

        ApiError error = new ApiError(
            400,
            "ERROR",
            errors 
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error);
    }
}