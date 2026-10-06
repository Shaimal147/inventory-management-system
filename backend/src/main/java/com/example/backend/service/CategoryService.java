package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.*;
import com.example.backend.entity.*;
import com.example.backend.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    public Page<CategoryResponseDto> getCategories(int page, int size) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CategoryEntity> categories = categoryRepository.findAll(pageable);
        Page<CategoryResponseDto> response = categories.map(category -> new CategoryResponseDto(
            category.getId(),
            category.getName(),
            category.getDescription()
        ));

        return response;
    }
}
