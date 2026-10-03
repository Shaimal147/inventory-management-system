package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.*;

@Service 
public class ProductService {
    public String createProduct(CreateProductDto newProduct) {
        return "Successfully created product";
    }
}
