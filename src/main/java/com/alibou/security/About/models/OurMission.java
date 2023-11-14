package com.alibou.security.About.models;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission_section")
public class OurMission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column(name ="content")
    private String content;

    @Lob
    @Column(name = "img", columnDefinition = "LONGBLOB")
    private byte[] img;

    @Column(name = "img_type")
    private String imgType;

    @Column(name = "img_name")
    private String imgName;
    

}
