package com.alibou.security.Blog.model;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 1000)
    private String content;
    @Column(name = "author", length = 1000)
    private String author;

    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] img;

    private Date createdDate;

    private Date updatedDate;


    @ManyToOne()
    @JoinColumn(name = "catId")
    private Category category;
    
    @ManyToOne()
    private User user;


}
