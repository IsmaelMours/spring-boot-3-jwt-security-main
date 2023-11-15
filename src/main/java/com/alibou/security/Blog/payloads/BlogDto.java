package com.alibou.security.Blog.payloads;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class BlogDto {
    private Long blogId;
    private String title;
    private String author;
    private String content;
    private byte[] img;
    private Date createdDate;
    private Date updatedDate;
    private CategoryDto category;
    //private UserDto user;
}
