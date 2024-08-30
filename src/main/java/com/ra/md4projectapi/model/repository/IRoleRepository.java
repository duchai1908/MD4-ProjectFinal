package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.constants.RoleName;
import com.ra.md4projectapi.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
