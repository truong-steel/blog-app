package com.vti.service;

import com.vti.dto.UserDto;
import com.vti.form.UserCreateForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto create(UserCreateForm form);
}
