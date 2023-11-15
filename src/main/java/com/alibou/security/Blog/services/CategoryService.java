package com.alibou.security.Blog.services;

import com.alibou.security.Blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createcategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategories();

    public CategoryDto getCategoryById(Integer id);

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    public void deleteCategory(Integer id);
}
