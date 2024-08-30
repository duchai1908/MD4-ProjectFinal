package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IOrdersService;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/orders")
@RequiredArgsConstructor
public class OrdersUserController {
    private final IOrdersService ordersService;
    @GetMapping
    public ResponseEntity<?> getOrdersUser(@AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.getAllOrdersByUserLogin(userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> getOrdersUserBySku(@PathVariable("sku") String sku, @AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.getOrdersBySku(sku,userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("list/{status}")
    public ResponseEntity<?> getOrdersByStatus(@PathVariable("status") String status, @AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.findOrderByStatus(status,userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable("orderId") Long orderId, @AuthenticationPrincipal UserDetailCustom userDetailCustom) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.cancelOrder(orderId,userDetailCustom.getUsers().getId()), HttpStatus.OK),HttpStatus.OK);
    }

}
