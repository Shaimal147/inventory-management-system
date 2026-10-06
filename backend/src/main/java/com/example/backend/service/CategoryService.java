package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.*;
import com.example.backend.entity.*;
import com.example.backend.repository.*;

@Service 
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto) {
        CategoryEntity category = new CategoryEntity();

        category.setName(createCategoryDto.name());
        category.setDescription(createCategoryDto.description());
        
        CategoryEntity savedCategory = categoryRepository.save(category);

        CategoryResponseDto response = new CategoryResponseDto(
            savedCategory.getId(),
            savedCategory.getName(),
            savedCategory.getDescription()
        );

        return response;
    }
}
