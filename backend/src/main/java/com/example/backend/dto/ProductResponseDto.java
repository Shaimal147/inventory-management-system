package com.example.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String name,
        String sku,
        String description,
        BigDecimal price,
        int quantity,
        int reorderLevel,
        Long categoryId,
        String supplier,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
