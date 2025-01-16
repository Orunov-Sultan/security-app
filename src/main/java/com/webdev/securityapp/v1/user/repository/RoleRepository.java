package com.webdev.securityapp.v1.user.repository;

import com.webdev.securityapp.v1.user.dto.UserDto;
import com.webdev.securityapp.v1.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
