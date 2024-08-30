package com.ra.md4projectapi.controller.admin;

import com.ra.md4projectapi.model.dto.request.ChangePasswordRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user1/account")
@RequiredArgsConstructor
public class UserAccountController {
    private final IUserService userService;
    // Change Password
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws BadRequestException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.changePassword(changePasswordRequest), HttpStatus.OK),HttpStatus.OK);
    }
}
