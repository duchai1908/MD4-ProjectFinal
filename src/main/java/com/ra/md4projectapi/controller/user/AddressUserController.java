package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.model.dto.request.AddressRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IAddressService;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/address")
@RequiredArgsConstructor
public class AddressUserController {
    private final IAddressService addressService;

    //Get All Address By UserLoginId
    @GetMapping
    public ResponseEntity<?> getAllAdress(@AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(addressService.findAll(userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }

    // Get Address By AddressId And UserLoginId
    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable Long addressId, @AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(addressService.findById(addressId,userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }

    // Add New Address
    @PostMapping
    public ResponseEntity<?> addNewAddress(@RequestBody AddressRequest addressRequest, @AuthenticationPrincipal UserDetailCustom userDetailCustom){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(addressService.save(addressRequest,userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }

    // Delete Address By Id
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@AuthenticationPrincipal UserDetailCustom userDetailCustom, @PathVariable Long addressId){
        addressService.deleteById(addressId,userDetailCustom.getUsers().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
