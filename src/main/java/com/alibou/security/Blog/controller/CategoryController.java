package com.alibou.security.Blog.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategoryDto = this.categoryService.createcategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long id ) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDto, id));
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiRes> deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiRes>(
                new ApiRes("Category Deleted", true ), HttpStatus.OK
        );
    }

}
