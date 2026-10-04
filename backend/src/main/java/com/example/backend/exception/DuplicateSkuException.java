package com.example.backend.exception;

public class DuplicateSkuException extends RuntimeException{
    public DuplicateSkuException(String message) {
        super(message);
    }
}
