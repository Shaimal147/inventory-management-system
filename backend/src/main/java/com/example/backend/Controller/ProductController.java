package com.example.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CreateProductResponseDto> createProduct(@Valid @RequestBody CreateProductDto newProduct) {
        CreateProductResponseDto response = productService.createProduct(newProduct);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }
}
