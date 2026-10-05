package com.example.backend.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.backend.dto.*;
import com.example.backend.entity.CategoryEntity;
import com.example.backend.entity.ProductEntity;
import com.example.backend.entity.SupplierEntity;
import com.example.backend.repository.*;
import com.example.backend.exception.*;

@Service 
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        SupplierRepository supplierRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public ProductResponseDto createProduct(CreateProductDto newProduct) {
        ProductEntity product = new ProductEntity();

        if(productRepository.existsBySku(newProduct.sku())) {
            throw new DuplicateSkuException("Sku already exists");
        }

        CategoryEntity category = categoryRepository.findById(newProduct.categoryId()).orElseThrow(
            () -> new ResourceNotFoundException("Category not found with ID: %d".formatted(newProduct.categoryId()))
        );

        SupplierEntity supplier = supplierRepository.findById(newProduct.supplierId()).orElseThrow(
            () -> new ResourceNotFoundException("Supplier not found with ID : %d".formatted(newProduct.supplierId()))
        );

        product.setName(newProduct.name());
        product.setSku(newProduct.sku());
        product.setDescription(newProduct.description());
        product.setPrice(newProduct.price());
        product.setQuantity(newProduct.quantity());
        product.setReorderLevel(newProduct.reorderLevel());
        product.setCategory(category);
        product.setSupplier(supplier);

        ProductEntity savedProduct = productRepository.save(product);

        ProductResponseDto productResponse = new ProductResponseDto(
            savedProduct.getId(),
            savedProduct.getName(),
            savedProduct.getSku(),
            savedProduct.getDescription(),
            savedProduct.getPrice(),
            savedProduct.getQuantity(),
            savedProduct.getReorderLevel(),
            savedProduct.getCategory().getId(),
            savedProduct.getSupplier().getName(),
            savedProduct.getCreatedAt(),
            savedProduct.getUpdatedAt()
        );

        return productResponse;
    }

    public GetProductDto getProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with ID: %d".formatted(id))
        );

        GetProductDto productResponse = new GetProductDto(
            product.getId(),
            product.getName(),
            product.getSku(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity(),
            product.getReorderLevel(),
            product.getCategory().getId(),
            product.getSupplier().getName(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );

        return productResponse;
    }

    public Page<GetProductDto> getProducts(
        int page,
        int size,
        Long categoryId,
        Long supplierId,
        Boolean lowStock,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String searchKeyword
    ) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductEntity> products;
        CategoryEntity category = null;
        SupplierEntity supplier = null;

        if (categoryId != null) {
            category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with ID: %d".formatted(categoryId))
            );
        }

        if (supplierId != null) {
            supplier = supplierRepository.findById(supplierId).orElseThrow(
                () -> new ResourceNotFoundException("Supplier not found with ID: %d".formatted(categoryId))
            );
        }

        products = productRepository.findFiltered(category, supplier, lowStock, minPrice, maxPrice, searchKeyword, pageable);    


        Page<GetProductDto> productsResponse = products.map(product -> new GetProductDto(
            product.getId(),
            product.getName(),
            product.getSku(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity(),
            product.getReorderLevel(),
            product.getCategory().getId(),
            product.getSupplier().getName(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        ));

        return productsResponse;
    }

    public ProductResponseDto updateProductDetails(Long id, UpdateProductDto updateProductDto) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with ID: %d".formatted(id))
        );

        if (updateProductDto.name() != null) {
            product.setName(updateProductDto.name());
        }

        if (updateProductDto.sku() != null) {
            product.setSku(updateProductDto.sku());
        }

        if (updateProductDto.description() != null) {
            product.setDescription(updateProductDto.description());
        }

        if (updateProductDto.price() != null) {
            product.setPrice(updateProductDto.price());
        }

        if (updateProductDto.quantity() != null) {
            product.setQuantity(updateProductDto.quantity());
        }

        if (updateProductDto.reorderLevel() != null) {
            product.setReorderLevel(updateProductDto.reorderLevel());
        }

        if (updateProductDto.categoryId() != null) {
            CategoryEntity category = categoryRepository.findById(updateProductDto.categoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with ID: %d".formatted(updateProductDto.categoryId()))
            );
            product.setCategory(category);
        }

        if (updateProductDto.supplierId() != null) {
            SupplierEntity supplier = supplierRepository.findById(updateProductDto.supplierId()).orElseThrow(
                () -> new ResourceNotFoundException("Supplier not found with ID: %d".formatted(updateProductDto.supplierId()))
            );
            product.setSupplier(supplier);
        }

        ProductEntity updatedProduct = productRepository.save(product);

        ProductResponseDto productResponse = new ProductResponseDto(
            updatedProduct.getId(),
            updatedProduct.getName(),
            updatedProduct.getSku(),
            updatedProduct.getDescription(),
            updatedProduct.getPrice(),
            updatedProduct.getQuantity(),
            updatedProduct.getReorderLevel(),
            updatedProduct.getCategory().getId(),
            updatedProduct.getSupplier().getName(),
            updatedProduct.getCreatedAt(),
            updatedProduct.getUpdatedAt()
        );

        return productResponse;
    }

    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
            () -> new  ResourceNotFoundException("Product not found with ID: %d".formatted(id))
        );

        productRepository.delete(product);
    }
}
