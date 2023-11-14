package com.alibou.security.Blog.repository;

import com.springbootdashboard.schoolbackend.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
