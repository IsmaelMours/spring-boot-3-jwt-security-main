package com.alibou.security.Blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotEmpty
    @Size(min = 6, message = "Username must be a minimum of 6 characters")
    private String name;


    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty
    @Size(min = 6, message = "password must be a minimum of 6 characters")
    private String password;

}
