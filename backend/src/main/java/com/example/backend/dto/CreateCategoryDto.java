package com.example.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCategoryDto(
    @NotNull
    @Size (min = 1, max = 50) 
    String name,

    @NotNull
    @Size (max = 100) 
    String description
) {
}
