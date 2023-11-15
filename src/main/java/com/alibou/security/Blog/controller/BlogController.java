package com.alibou.security.Blog.controller;

import com.alibou.security.Blog.payloads.ApiRes;
import com.alibou.security.Blog.payloads.BlogDto;
import com.alibou.security.Blog.payloads.BlogResponse;
import com.alibou.security.Blog.services.BlogService;
import com.alibou.security.Blog.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService postService;

    @PostMapping("/category/{catId}/user/{userId}")
    public ResponseEntity<String> createBlog(@RequestPart("data") BlogDto blogDto, @PathVariable Integer catId,
                                             @PathVariable Long userId, @RequestPart("img") MultipartFile file) {
        try{
            blogDto.setImg(file.getBytes());
        }catch(IOException e) {
            e.printStackTrace();
        }
        BlogDto createPost = this.postService.createBlog(blogDto, catId, userId);
        return ResponseEntity.ok().body(
                blogDto.getTitle() + blogDto.getContent() + blogDto.getImg()
        );
    }

    @GetMapping("/")
    public ResponseEntity<BlogResponse> getAllBlogs(
            @RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir
    ) {
        BlogResponse blogResponse = this.postService.getAllBlogs(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.OK);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDto> getPostId(@PathVariable Integer blogId) {
        BlogDto blogDto = this.postService.getBlogById(blogId);
        return new ResponseEntity<BlogDto>(blogDto, HttpStatus.OK);
    }

    @GetMapping("/category/{catId}")
    public ResponseEntity<List<BlogDto>> getBlogByCategory(@PathVariable Integer catId) {
        List<BlogDto> blogDtos = this.postService.getBlogByCategory(catId);
        return new ResponseEntity<List<BlogDto>>(blogDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BlogDto>> getPostByUser(@PathVariable Long userId) {
        List<BlogDto> blogDtos = this.postService.getBlogByUser(userId);
        return new ResponseEntity<List<BlogDto>>(blogDtos, HttpStatus.OK);
    }

    @DeleteMapping("{blogId}")
    public ApiRes deleteBlog(@PathVariable Integer blogId) {
        this.postService.deleteBlog(blogId);
        return new ApiRes("Blog is Deleted successfully ", true);
    }

    @PutMapping("{blogId}")
    public ResponseEntity<String> updateBlog(@RequestPart("data") BlogDto blogDto, @PathVariable Integer blogId, @RequestPart("img") MultipartFile file) {
        try{
            blogDto.setImg(file.getBytes());
        }catch(IOException e) {
            e.printStackTrace();
        }
        BlogDto updateBlog = this.postService.updateblog(blogDto, blogId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<BlogDto>> searchTitle(@PathVariable("title") String title) {
        List<BlogDto> blogDtos = this.postService.searchBlog(title);

        return new ResponseEntity<List<BlogDto>>(blogDtos, HttpStatus.OK);
    }

    @PostMapping("/img/upload/{blogId}")
    public BlogDto uploadImg(@PathVariable Integer blogId, @RequestParam("img") MultipartFile img) {
        BlogDto blog = this.postService.getBlogById(blogId);
        try {
            blog.setImg(img.getBytes());
        } catch (IOException ex) {

        }
        return postService.updateblog(blog, blogId);
    }

    @GetMapping(value = "/file/{blogId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadFile(@PathVariable("blogId") Integer blogId) {
        BlogDto blog = postService.getBlogById(blogId);
        byte[] image = blog.getImg();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

}
