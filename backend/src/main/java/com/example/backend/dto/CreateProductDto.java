package com.example.backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateProductDto(

    @NotBlank  
    String name,

    @NotBlank
    String sku,

    @Size (min = 0, max = 50)
    String description,

    @Positive 
    BigDecimal price,

    @PositiveOrZero 
    int quantity,

    @PositiveOrZero 
    int reorderLevel,

    @NotNull 
    @Positive  
    Long categoryId,

    @NotBlank  
    String supplier // Supplier stored as just a String for now
) {
}
