package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.model.dto.request.ChangePasswordRequest;
import com.ra.md4projectapi.model.dto.request.UserAccountRequest;
import com.ra.md4projectapi.model.dto.request.UserRequest;
import com.ra.md4projectapi.model.entity.User;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> getUsers(Pageable pageable, String search);
    UserDetailCustom getCurrentUserLogin();
    User getUserById(Long id);
    User getUserByUserLogin();
    User updateUser(Long id, UserRequest userRequest);
    User updateUserAccount(UserAccountRequest userAccountRequest);
    User changeStatus(Long id);
    User changePassword(ChangePasswordRequest changePasswordRequest) throws BadRequestException;
    User save(User user);
}
