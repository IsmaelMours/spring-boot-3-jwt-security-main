package com.alibou.security.Blog.service.impl;

import com.alibou.security.Blog.payloads.BlogDto;
import com.alibou.security.Blog.exceptions.ResourceNotFoundException;
import com.springbootdashboard.schoolbackend.Blog.model.Blog;
import com.springbootdashboard.schoolbackend.Blog.model.Category;
import com.springbootdashboard.schoolbackend.Blog.model.User;
import com.alibou.security.Blog.payloads.BlogResponse;
import com.alibou.security.Blog.repository.BlogRepository;
import com.alibou.security.Blog.repository.CategoryRepository;
import com.alibou.security.Blog.repository.FileUploadRepository;
import com.alibou.security.Blog.repository.UserRepository;
import com.alibou.security.Blog.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired

    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;
    @Override
    public BlogDto createBlog(BlogDto blogDto, Long catId, Long userId )  {
        Category category = this.categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", catId));
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));

        Blog blog = this.modelMapper.map(blogDto, Blog.class);
        blog.setCreatedDate(new Date());
        blog.setCategory(category);
        blog.setUser(user);
        Blog newBlog = this.blogRepository.save(blog);
        return this.modelMapper.map(newBlog, BlogDto.class);
    }

    @Override
    public BlogResponse getAllBlogs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Blog> blogPage = this.blogRepository.findAll(pageable);

        List<Blog> blogs = blogPage.getContent();

        List<BlogDto> blogDtos = blogs.stream().map((blog) -> this.modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());

        BlogResponse blogResponse = new BlogResponse();

        blogResponse.setContent(blogDtos);
        blogResponse.setPageNumber(blogPage.getNumber());
        blogResponse.setPageSize(blogPage.getSize());
        blogResponse.setTotalElements(blogPage.getTotalElements());
        blogResponse.setTotalPages(blogPage.getTotalPages());
        blogResponse.setLastpage(blogPage.isLast());

        return blogResponse;
    }


    @Override
    public BlogDto getBlogById(Long blogId) {
        Blog blog = this.blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "blodId", blogId));

        return this.modelMapper.map(blog, BlogDto.class);
    }

    @Override
    public BlogDto updateblog(BlogDto blogDto, Long blogId) {
        Blog blog = this.blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "BlogID", blogId));
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setImg(blogDto.getImg());
        blog.setAuthor(blogDto.getAuthor());
        blog.setUpdatedDate(blogDto.getUpdatedDate());

        Blog updatedBlog = this.blogRepository.save(blog);

        return this.modelMapper.map(updatedBlog, BlogDto.class);
    }

    @Override
    public void deleteBlog(Long blogId) {
        Blog blog = this.blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "BlogID", blogId));
        this.blogRepository.delete(blog);
    }

    @Override
    public List<BlogDto> getBlogByCategory(Long catId) {

        Category category = this.categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CatID", catId));

        List<Blog> blogs = this.blogRepository.findAllByCategory(category);

        List<BlogDto> blogDtos = blogs.stream().map((blog) -> this.modelMapper.map(blogs, BlogDto.class))
                .collect(Collectors.toList());


        return blogDtos;
    }

    @Override
    public List<BlogDto> getBlogByUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user","UserID",userId));

        List<Blog> blogs = this.blogRepository.findAllByUser(user);
        List<BlogDto> blogDtos = blogs.stream().map((blog) -> this.modelMapper.map(blogs, BlogDto.class))
                .collect(Collectors.toList());

        return blogDtos;
    }


    @Override
    public List<BlogDto> searchBlog(String title) {
        List<Blog> blogs = this.blogRepository.searchByTitle("%"+title+"%");
        List<BlogDto> blogDtos = blogs.stream().map((blog) -> this.modelMapper.map(blogs, BlogDto.class))
                .collect(Collectors.toList());
        return blogDtos;
    }
    public File store(MultipartFile imgData) throws IOException {
        File file = new File(imgData.getOriginalFilename());
        imgData.transferTo(file);
        System.out.println(file.toString());
        Blog blog = new Blog();
        blog.setImg(imgData.getBytes());
        return file;
    }


}
