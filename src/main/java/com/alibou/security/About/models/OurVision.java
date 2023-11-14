package com.alibou.security.About.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "vision_section"
        , uniqueConstraints = {@UniqueConstraint(columnNames =
        {"content", "img_name", "img_type", "img"})})
@AllArgsConstructor
@NoArgsConstructor
public class OurVision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "img_type")
    private String imgType;

    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] img;

}
