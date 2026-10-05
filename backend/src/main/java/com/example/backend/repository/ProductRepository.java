package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.backend.entity.CategoryEntity;
import com.example.backend.entity.ProductEntity;
import com.example.backend.entity.SupplierEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
        boolean existsBySku(String sku);

        @Query (
            """
                SELECT p
                FROM ProductEntity p
                WHERE (:category IS NULL OR p.category = :category)
                AND (:supplier IS NULL OR p.supplier = :supplier) 
                AND (
                    :lowStock IS NULL 
                    OR (:lowStock = TRUE AND p.quantity <= p.reorderLevel) 
                    OR (:lowStock = FALSE AND p.quantity > p.reorderLevel)
                    )
                AND (:minPrice IS NULL OR p.price >= :minPrice)
                AND (:maxPrice IS NULL OR p.price <= :maxPrice)
                AND (
                    LOWER(p.name) LIKE LOWER(CONCAT ('%', :searchKeyword, '%'))
                    OR
                    :searchKeyword IS NULL
                    ) 
            """
        )
        Page<ProductEntity> findFiltered(
            @Param ("category") CategoryEntity category,
            @Param ("supplier") SupplierEntity supplier,
            @Param ("lowStock") Boolean lowStock,
            @Param ("minPrice") BigDecimal minPrice,
            @Param ("maxPrice") BigDecimal maxPrice,
            @Param ("searchKeyword") String searchKeyword,
            Pageable pageable
        );
}
