package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.*;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    
}
