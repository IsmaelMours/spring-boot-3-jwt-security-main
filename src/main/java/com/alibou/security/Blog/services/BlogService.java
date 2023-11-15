package com.alibou.security.Blog.services;

import com.alibou.security.Blog.payloads.*;

import java.util.List;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto, Integer catId, Integer userId);

    BlogResponse getAllBlogs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    BlogDto getBlogById(Integer blogId);

    BlogDto updateblog(BlogDto blogDto, Integer blogId);

    void deleteBlog(Integer blogId);

    List<BlogDto> getBlogByUser(Integer userId);


    List<BlogDto> getBlogByCategory(Integer catId);

    List<BlogDto> searchBlog(String title);
}
