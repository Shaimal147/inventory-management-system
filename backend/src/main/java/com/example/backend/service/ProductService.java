package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.*;
import com.example.backend.entity.ProductEntity;
import com.example.backend.repository.ProductRepository;

@Service 
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createProduct(CreateProductDto newProduct) {
        ProductEntity product = new ProductEntity();

        product.setName(newProduct.name());
        product.setSku(newProduct.sku());
        product.setDescription(newProduct.description());
        product.setPrice(newProduct.price());
        product.setQuantity(newProduct.quantity());
        product.setReorderLevel(newProduct.reorderLevel());
        product.setCategory(newProduct.category());
        product.setSupplier(newProduct.supplier());

        productRepository.save(product);

        return "Successfully created product";
    }
}
