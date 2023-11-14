package com.alibou.security.About.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "about_section"
,uniqueConstraints ={@UniqueConstraint(columnNames = "description")})
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long Id;

    @Column (nullable=false, name = "description")
    private String aboutDescription;
    
}
