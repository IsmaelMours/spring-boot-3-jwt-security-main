package com.alibou.security.gallery.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="gallery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name ="event_name")
    private String eventName;

    @Lob
    @Column(name = "gallery_img", columnDefinition = "LONGBLOB")
    private byte[] img;

    @Column(name = "img_type")
    private String imgType;

    @Column(name = "img_name")
    private String imgName;

}