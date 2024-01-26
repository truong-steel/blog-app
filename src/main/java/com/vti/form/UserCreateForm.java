package com.vti.form;

import com.vti.dto.RoleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserCreateForm {
    private String name;
    private String username;
    private String email;
    private String password;
}
