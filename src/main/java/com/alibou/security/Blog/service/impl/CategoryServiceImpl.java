package com.alibou.security.Blog.service.impl;

import com.alibou.security.Blog.payloads.CategoryDto;
import com.alibou.security.Blog.exceptions.ResourceNotFoundException;
import com.springbootdashboard.schoolbackend.Blog.model.Category;
import com.alibou.security.Blog.repository.CategoryRepository;
import com.alibou.security.Blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createcategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addCategory = this.categoryRepository.save(category);
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category category = this.categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "Id",id));

        category.setCatTitle(categoryDto.getCatTitle());
        category.setCatDescription(categoryDto.getCatDescription());

        Category updateCategory = this.categoryRepository.save(category);

        return this.modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
