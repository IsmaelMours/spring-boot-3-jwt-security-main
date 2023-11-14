package com.alibou.security.Blog.service;

import com.alibou.security.Blog.payloads.UserDto;
import java.util.*;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long Id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user, Long Id);

    void deleteUser(Long Id);

}
