package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.constants.RoleName;
import com.ra.md4projectapi.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Role findByRoleName(RoleName roleName);
    RoleName findRoleNameByString(String roleName);
    List<Role> findAllRoles();
}
