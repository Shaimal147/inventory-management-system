package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryDto(
    @NotBlank 
    @Size (min = 1, max = 50) 
    String name,

    @NotBlank
    @Size (max = 100) 
    String description
) {
}
