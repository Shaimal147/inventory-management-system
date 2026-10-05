package com.example.backend.exception;

public class UnavailableQuantityException extends RuntimeException {
    public UnavailableQuantityException(String message) {
        super(message);
    }
}
