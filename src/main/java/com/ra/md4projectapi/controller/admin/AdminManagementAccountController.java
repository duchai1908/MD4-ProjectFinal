package com.ra.md4projectapi.controller.admin;

import com.ra.md4projectapi.model.dto.request.UserRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IRoleService;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/account")
@RequiredArgsConstructor
public class AdminManagementAccountController {
    private final IUserService userService;
    private final IRoleService roleService;
    // Get All User
    @GetMapping
    public ResponseEntity<?> getAccounts(@PageableDefault(page = 0,size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(defaultValue = "" )String search) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.getUsers(pageable,search), HttpStatus.OK), HttpStatus.OK);
    }
    // Find User By Id
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long userId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.getUserById(userId), HttpStatus.OK), HttpStatus.OK);
    }
    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long userId, @ModelAttribute UserRequest userRequest){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.updateUser(userId, userRequest), HttpStatus.OK), HttpStatus.OK);
    }
    // Change Status Account
    @PutMapping("/changeStatus/{userId}")
    public ResponseEntity<?> changeStatus(@PathVariable Long userId){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(userService.changeStatus(userId), HttpStatus.OK), HttpStatus.OK);
    }
    // Get All Role
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(roleService.findAllRoles(), HttpStatus.OK), HttpStatus.OK);
    }
}
