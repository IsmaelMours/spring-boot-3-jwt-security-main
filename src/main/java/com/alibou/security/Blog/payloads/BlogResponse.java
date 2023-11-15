package com.alibou.security.Blog.payloads;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class BlogResponse {
    private List<BlogDto> content;
    private String img;
    private int pageNumber;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean lastpage;
}
