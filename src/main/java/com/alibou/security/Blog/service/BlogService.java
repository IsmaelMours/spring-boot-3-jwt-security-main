package com.alibou.security.Blog.service;

import com.alibou.security.Blog.payloads.BlogDto;
import com.alibou.security.Blog.payloads.BlogResponse;

import java.util.List;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto, Long catId, Long userId);

    BlogResponse getAllBlogs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    BlogDto getBlogById(Long blogId);

    BlogDto updateblog(BlogDto blogDto, Long blogId);

    void deleteBlog(Long blogId);

    List<BlogDto> getBlogByUser(Long userId);


    List<BlogDto> getBlogByCategory(Long catId);
    List<BlogDto> searchBlog(String title);

}
