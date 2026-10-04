package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.*;
import com.example.backend.entity.ProductEntity;
import com.example.backend.repository.ProductRepository;
import com.example.backend.exception.*;

@Service 
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CreateProductResponseDto createProduct(CreateProductDto newProduct) {
        ProductEntity product = new ProductEntity();

        if(productRepository.existsBySku(newProduct.sku())) {
            throw new DuplicateSkuException("Sku already exists");
        }

        product.setName(newProduct.name());
        product.setSku(newProduct.sku());
        product.setDescription(newProduct.description());
        product.setPrice(newProduct.price());
        product.setQuantity(newProduct.quantity());
        product.setReorderLevel(newProduct.reorderLevel());
        product.setCategory(newProduct.category());
        product.setSupplier(newProduct.supplier());

        ProductEntity savedProduct = productRepository.save(product);

        CreateProductResponseDto productResponse = new CreateProductResponseDto(
            savedProduct.getId(),
            savedProduct.getName(),
            savedProduct.getSku(),
            savedProduct.getDescription(),
            savedProduct.getPrice(),
            savedProduct.getQuantity(),
            savedProduct.getReorderLevel(),
            savedProduct.getCategory(),
            savedProduct.getSupplier(),
            savedProduct.getCreatedAt(),
            savedProduct.getUpdatedAt()
        );

        return productResponse;
    }
}
