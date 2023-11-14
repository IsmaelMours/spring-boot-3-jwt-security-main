package com.alibou.security.Blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    @NotBlank
    @Size(min = 5, message = "Minimum Characters of title should be at least 5")
    private String CatTitle;


    @NotBlank
    @Size(min = 15, message = "Minimum Characters of Description should be at least 15")
    private String CatDescription;


}
