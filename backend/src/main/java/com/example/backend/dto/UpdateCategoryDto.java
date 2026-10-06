package com.example.backend.dto;

import jakarta.validation.constraints.Size;

public record UpdateCategoryDto(
    @Size (min = 1, max = 50)
    String name,

    @Size (min = 1, max = 100)
    String description
) {
}
