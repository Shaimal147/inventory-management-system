package com.example.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;

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

    @GetMapping 
    public ResponseEntity<Page<CategoryResponseDto>> getCategories(
        @RequestParam (value = "page", defaultValue = "0") int page,
        @RequestParam (value = "size", defaultValue = "20") int size
    ) {
        Page<CategoryResponseDto> response = categoryService.getCategories(page, size);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
        @PathVariable Long id,
        @Valid @RequestBody UpdateCategoryDto updateCategoryDto 
    ) {
        CategoryResponseDto response = categoryService.updateCategory(id, updateCategoryDto);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }
}
