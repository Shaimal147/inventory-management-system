package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.backend.entity.CategoryEntity;
import com.example.backend.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
        boolean existsBySku(String sku);

        @Query (
            """
                SELECT p
                FROM ProductEntity p
                WHERE (:category IS NULL OR p.category = :category) 
                AND (
                    :lowStock IS NULL 
                    OR (:lowStock = TRUE AND p.quantity <= p.reorderLevel) 
                    OR (:lowStock = FALSE AND p.quantity > p.reorderLevel)
                    ) 
            """
        )
        Page<ProductEntity> findFiltered(@Param ("category") CategoryEntity category, @Param ("lowStock") Boolean lowStock, Pageable pageable);
}
