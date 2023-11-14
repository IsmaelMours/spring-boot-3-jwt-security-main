package com.alibou.security.Blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long catId;

    @Column(name = "title", length = 100, nullable = false)
    private String catTitle;

    @Column(name = "description")
    private String catDescription;


    @OneToMany(mappedBy = "category", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blog> blogs = new ArrayList<>();


}
