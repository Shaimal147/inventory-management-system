package com.example.backend.dto;

import java.math.BigDecimal;

public record UpdateProductDto(
	String name,
	String sku,
	String description,
	BigDecimal price,
	Integer quantity,
	Integer reorderLevel,
	Long categoryId,
	Long supplierId) {
}
