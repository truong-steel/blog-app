package com.vti.repository;

import com.vti.dto.UserDto;
import com.vti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
