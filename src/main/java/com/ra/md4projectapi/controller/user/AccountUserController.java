package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.model.dto.request.ChangePasswordRequest;
import com.ra.md4projectapi.model.dto.request.UserAccountRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/account")
@RequiredArgsConstructor
public class AccountUserController {
    private final IUserService userService;

    // Change Password
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws BadRequestException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.changePassword(changePasswordRequest), HttpStatus.OK),HttpStatus.OK);
    }

    // Update User
    @PutMapping()
    public ResponseEntity<?> updateAccount(@ModelAttribute UserAccountRequest userAccountRequest){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.updateUserAccount(userAccountRequest), HttpStatus.OK), HttpStatus.OK);
    }

    // Get UserLogin
    @GetMapping()
    public ResponseEntity<?> getAccount(){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.getUserByUserLogin(), HttpStatus.OK), HttpStatus.OK);
    }
}
