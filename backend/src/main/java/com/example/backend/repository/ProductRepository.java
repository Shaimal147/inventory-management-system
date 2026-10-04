package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.backend.entity.CategoryEntity;
import com.example.backend.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
        boolean existsBySku(String sku);
        Page<ProductEntity> findByCategory(CategoryEntity categoryEntity, Pageable pageable);
}
