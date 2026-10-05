package com.example.backend.dto;

import java.time.LocalDateTime;

import com.example.backend.enums.StockMovementEnum;

public record StockMovementResponseDto(
	Long id,
	Long productId,
	StockMovementEnum type,
	int quantity,
	String reason,
	LocalDateTime createdAt) {
}
