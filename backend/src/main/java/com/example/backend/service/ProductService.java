package com.example.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public GetProductDto getProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with id: %d".formatted(id))
        );

        GetProductDto productResponse = new GetProductDto(
            product.getId(),
            product.getName(),
            product.getSku(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity(),
            product.getReorderLevel(),
            product.getCategory(),
            product.getSupplier(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );

        return productResponse;
    }

    public Page<GetProductDto> getProducts(int page, int size) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductEntity> products = productRepository.findAll(pageable);

        if (products == null) {
            throw new ResourceNotFoundException("No products found");
        }

        Page<GetProductDto> productsResponse = products.map(product -> new GetProductDto(
            product.getId(),
            product.getName(),
            product.getSku(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity(),
            product.getReorderLevel(),
            product.getCategory(),
            product.getSupplier(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        ));

        return productsResponse;
    }
}
