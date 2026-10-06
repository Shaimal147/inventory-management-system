package com.example.backend.exception;

import java.util.List;
import java.util.Map;

public record ApiError(int status, String message, Map<String, List<String>> errors) {
    
}
