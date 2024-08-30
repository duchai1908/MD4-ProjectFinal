package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.model.dto.request.CartItemRequest;
import com.ra.md4projectapi.model.dto.request.OrdersRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.ICartService;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartUserController {
    private final ICartService cartService;
    // Add To Cart
    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest cartItemRequest, @AuthenticationPrincipal UserDetailCustom userDetailCustom){
        cartItemRequest.setUser(userDetailCustom.getUsers().getId());
        return new ResponseEntity<>(new ResponseDtoSuccess<>(cartService.add(cartItemRequest),HttpStatus.CREATED), HttpStatus.CREATED);
    }

    // List Cart
    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetailCustom userDetailCustom){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(cartService.getCartItems(userDetailCustom.getUsers().getId()),HttpStatus.OK), HttpStatus.OK);
    }

    // ChangeQuantity
    @PutMapping("/{cartId}/{quantity}")
    public ResponseEntity<?> updateQuantity(@PathVariable("cartId") Long cartId, @PathVariable("quantity") Integer quantity){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(cartService.changeQuantity(cartId,quantity),HttpStatus.OK),HttpStatus.OK);
    }

    // DeleteById
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable("cartId") Long cartId){
        cartService.deleteCartItem(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete All CartItem
    @DeleteMapping
    public ResponseEntity<?> deleteCartItem(@AuthenticationPrincipal UserDetailCustom userDetailCustom){
        cartService.deleteAllCartItems(userDetailCustom.getUsers().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Add to Order
    @PostMapping("/addToOrder")
    public ResponseEntity<?> addToOrder(@RequestBody OrdersRequest ordersRequest, @AuthenticationPrincipal UserDetailCustom userDetailCustom){
        return new ResponseEntity<>(new ResponseDtoSuccess<>(cartService.addOrders(ordersRequest,userDetailCustom.getUsers().getId()),HttpStatus.CREATED),HttpStatus.CREATED);
    }

}
