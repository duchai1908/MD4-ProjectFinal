package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.model.dto.request.ChangePasswordRequest;
import com.ra.md4projectapi.model.dto.request.UserAccountRequest;
import com.ra.md4projectapi.model.dto.request.UserRequest;
import com.ra.md4projectapi.model.entity.Role;
import com.ra.md4projectapi.model.entity.User;
import com.ra.md4projectapi.model.repository.IRoleRepository;
import com.ra.md4projectapi.model.repository.IUserRepository;
import com.ra.md4projectapi.model.service.IRoleService;
import com.ra.md4projectapi.model.service.IUserService;
import com.ra.md4projectapi.model.service.UploadService;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UploadService uploadService;
    @Override
    public Page<User> getUsers(Pageable pageable, String search) {
        Page<User> users;
        if (search == null || search.isEmpty()) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.findUserByUsernameContainsIgnoreCase(search, pageable);
        }
        return users;
    }

    @Override
    public UserDetailCustom getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                return (UserDetailCustom) authentication.getPrincipal();
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
    }

    @Override
    public User getUserByUserLogin() {
        return getUserById(getCurrentUserLogin().getUsers().getId());
    }

    @Override
    public User updateUser(Long id, UserRequest userRequest) {
        Set<Role> roles = new HashSet<>();
        userRequest.getRoles().forEach(item -> roles.add(roleRepository.findByRoleName(roleService.findRoleNameByString(item)).orElseThrow(()-> new NoSuchElementException("RoleName not found"))));
        User user = getUserById(id);
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getImage().getSize() >0){
            user.setImage(uploadService.uploadFileToServer(userRequest.getImage()));
        }
        user.setDob(userRequest.getDob());
        user.setStatus(userRequest.getStatus());
        user.setUpdatedAt(new Date());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateUserAccount(UserAccountRequest userAccountRequest) {
        User user = getUserById(getCurrentUserLogin().getUsers().getId());
        user.setFullName(userAccountRequest.getFullName());
        user.setEmail(userAccountRequest.getEmail());
        user.setAddress(userAccountRequest.getAddress());
        user.setPhone(userAccountRequest.getPhone());
        if(userAccountRequest.getImage().getSize() >0){
            user.setImage(uploadService.uploadFileToServer(userAccountRequest.getImage()));
        }
        user.setDob(userAccountRequest.getDob());
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User changeStatus(Long id) {
        User user = getUserById(id);
        user.setStatus(!user.getStatus());
        return userRepository.save(user);
    }

    @Override
    public User changePassword(ChangePasswordRequest changePasswordRequest) throws BadRequestException {
        UserDetailCustom userDetailCustom = getCurrentUserLogin();
        if (userDetailCustom != null) {
            User user = getUserById(userDetailCustom.getUsers().getId());
            if(!passwordEncoder.matches(changePasswordRequest.getOldPassword(),user.getPassword())) {
                throw new BadRequestException("Old password does not match");
            }
            else if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
                throw new BadRequestException("Confirm password does not match");
            }
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            return userRepository.save(user);
        }else{
            throw new NoSuchElementException("User not found");
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
