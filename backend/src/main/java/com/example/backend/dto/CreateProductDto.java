package com.example.backend.dto;

import java.math.BigDecimal;

public record CreateProductDto(
    String name,
    String sku,
    String description,
    BigDecimal price,
    int quantity,
    String category, // category stored as just a String for now
    String supplier // Supplier stored as just a String for now
) {
}
