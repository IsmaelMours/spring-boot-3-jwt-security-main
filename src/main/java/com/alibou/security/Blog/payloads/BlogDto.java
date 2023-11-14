package com.alibou.security.Blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String imgType;
    private String imgName;
    private Date createdDate;
    private Date updatedDate;
    private CategoryDto category;
    private UserDto user;

}
