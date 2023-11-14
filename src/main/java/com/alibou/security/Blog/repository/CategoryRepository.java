package com.alibou.security.Blog.repository;

import com.springbootdashboard.schoolbackend.Blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
