package com.vti.controller;

import com.vti.dto.UserDto;
import com.vti.form.UserCreateForm;
import com.vti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    private final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

@PostMapping("/api/v1/users")
@ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserCreateForm form){
        return userService.create(form);
    }

}
