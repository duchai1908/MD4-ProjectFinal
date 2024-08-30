package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.constants.RoleName;
import com.ra.md4projectapi.model.entity.Role;
import com.ra.md4projectapi.model.repository.IRoleRepository;
import com.ra.md4projectapi.model.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;
    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("Role not found"));
    }

    @Override
    public RoleName findRoleNameByString(String roleName) {
        RoleName[] roleNames = RoleName.values();
        for(RoleName r : roleNames){
            if(r.toString().equals(roleName)){
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
