package com.example.backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record UpdateProductDto(
	@Size(min = 1, max = 255)
	String name,

	@Size(min = 1, max = 100)
	String sku,

	@Size(max = 1000)
	String description,

	@Positive 
	BigDecimal price,

	@PositiveOrZero 
	Integer reorderLevel,

	@Positive 
	Long categoryId,
	
	@Positive 
	Long supplierId) {
}
