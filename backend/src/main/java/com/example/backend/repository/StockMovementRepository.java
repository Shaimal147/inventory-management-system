package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.StockMovementEntity;

public interface StockMovementRepository extends JpaRepository<StockMovementEntity, Long> {
    
}
