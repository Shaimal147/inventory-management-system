package com.example.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.CategoryService;

import jakarta.validation.Valid;

import com.example.backend.dto.*;

@RestController
@RequestMapping ("/api/categories") 
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        CategoryResponseDto response = categoryService.createCategory(createCategoryDto);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }
}
