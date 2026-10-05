package com.example.backend.dto;

import com.example.backend.enums.StockMovementEnum;

public record CreateStockMovementDto(
	StockMovementEnum type,
	int quantity,
	String reason) {
}
