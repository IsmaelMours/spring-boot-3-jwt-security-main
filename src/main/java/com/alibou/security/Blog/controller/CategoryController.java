package com.alibou.security.Blog.controller;


import com.alibou.security.Blog.payloads.ApiRes;
import com.alibou.security.Blog.payloads.CategoryDto;
import com.alibou.security.Blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(catId));
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId ) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDto, catId));
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiRes> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiRes>(
                new ApiRes("Category Deleted", true ), HttpStatus.OK
        );
    }

}