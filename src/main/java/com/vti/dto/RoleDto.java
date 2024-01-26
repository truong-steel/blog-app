package com.vti.dto;

import com.vti.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private Long id;
    private Role.Type type;
}
