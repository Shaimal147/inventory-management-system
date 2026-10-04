package com.example.backend.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.exception.DuplicateSkuException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler (DuplicateSkuException.class)
    public ResponseEntity<String> handleDuplicateSku(DuplicateSkuException exception) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(exception.getMessage());
    }
}
