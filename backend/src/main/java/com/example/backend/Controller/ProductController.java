package com.example.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import java.math.BigDecimal;

import jakarta.validation.Valid;

import com.example.backend.dto.*;
import com.example.backend.service.*;

@RestController 
@RequestMapping ("/api/products") 
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping 
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody CreateProductDto newProduct) {
        ProductResponseDto response = productService.createProduct(newProduct);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<GetProductDto> getProduct(@PathVariable Long id) {
        GetProductDto response = productService.getProduct(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<GetProductDto>> getProducts(
        @RequestParam (value = "page", defaultValue = "0") int page,
        @RequestParam (value = "size", defaultValue = "20") int size,
        @RequestParam (required = false) Long categoryId,
        @RequestParam (required = false) Long supplierId,
        @RequestParam (required = false) Boolean lowStock,
        @RequestParam (required = false) BigDecimal minPrice,
        @RequestParam (required = false) BigDecimal maxPrice,
        @RequestParam (required = false) String searchKeyword
    ) {
        Page<GetProductDto> response = productService.getProducts(
            page,
            size,
            categoryId,
            supplierId,
            lowStock,
            minPrice,
            maxPrice,
            searchKeyword
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<ProductResponseDto> updateProductDetails(@PathVariable Long id, @RequestBody UpdateProductDto updateProductDto) {
        ProductResponseDto response = productService.updateProductDetails(id, updateProductDto);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Product successfully deleted");
    }
}
