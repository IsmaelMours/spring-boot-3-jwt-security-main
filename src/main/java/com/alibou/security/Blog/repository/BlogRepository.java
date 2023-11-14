package com.alibou.security.Blog.repository;

import com.springbootdashboard.schoolbackend.Blog.model.Blog;
import com.springbootdashboard.schoolbackend.Blog.model.Category;
import com.springbootdashboard.schoolbackend.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByUser(User user);

    List<Blog> findAllByCategory(Category category);

    @Query("select b from Blog b where b.title like :key")
    List<Blog> searchByTitle(@Param("key") String title);
}
