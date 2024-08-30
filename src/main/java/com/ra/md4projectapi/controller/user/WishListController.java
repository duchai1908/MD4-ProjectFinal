package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IWishlistService;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final IWishlistService wishlistService;
    @GetMapping
    public ResponseEntity<?> getWishList(@AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(wishlistService.getWishlist(userDetailCustom.getUsers().getId()), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/{proId}")
    public ResponseEntity<?> addWishList(@AuthenticationPrincipal UserDetailCustom userDetailCustom, @PathVariable Long proId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(wishlistService.addToWishlist(userDetailCustom.getUsers().getId(), proId), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{proId}")
    public ResponseEntity<?> removeWishList(@AuthenticationPrincipal UserDetailCustom userDetailCustom, @PathVariable Long proId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(wishlistService.removeFromWishlist(userDetailCustom.getUsers().getId(), proId), HttpStatus.OK), HttpStatus.OK);
    }
}
