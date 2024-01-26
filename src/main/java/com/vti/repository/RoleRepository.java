package com.vti.repository;

import com.vti.dto.RoleDto;
import com.vti.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByType(Role.Type type);
}
