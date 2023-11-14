package com.alibou.security.Blog.repository;

import com.springbootdashboard.schoolbackend.Blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<Blog, Long> {
}
