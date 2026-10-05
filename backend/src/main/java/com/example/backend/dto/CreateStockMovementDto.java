package com.example.backend.dto;

import com.example.backend.enums.StockMovementEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateStockMovementDto(
    @NotNull 
	StockMovementEnum type,

    @NotNull
    @Positive 
	Integer quantity,
    
	String reason) {
}
