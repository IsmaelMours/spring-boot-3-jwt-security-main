package com.alibou.security.Blog.service.impl;

import com.alibou.security.Blog.exceptions.ResourceNotFoundException;
import com.springbootdashboard.schoolbackend.Blog.model.User;
import com.alibou.security.Blog.payloads.UserDto;
import com.alibou.security.Blog.repository.UserRepository;
import com.alibou.security.Blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Configuration
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updateUser = this.userRepository.save(user);
        UserDto userDto1 = this.userToDto(updateUser);

        return userDto1;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        this.userRepository.delete(user);
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }
}
