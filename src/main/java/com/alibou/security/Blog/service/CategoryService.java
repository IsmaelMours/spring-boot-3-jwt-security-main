package com.alibou.security.Blog.service;

import com.alibou.security.Blog.payloads.CategoryDto;

import java.util.*;

public interface CategoryService {

    public CategoryDto createcategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategories();

    public CategoryDto getCategoryById(Long id);

    public CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    public void deleteCategory(Long id);

}
