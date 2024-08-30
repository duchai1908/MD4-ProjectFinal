package com.ra.md4projectapi.controller.admin;

import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final IOrdersService ordersService;

    // Get All Orders
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.getOrders(), HttpStatus.OK), HttpStatus.OK);
    }

    // Get OrderById
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.getOrdersById(orderId), HttpStatus.OK), HttpStatus.OK);
    }

    // Get Orders By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getOrdersByStatus(@PathVariable String status) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.findByStatus(status), HttpStatus.OK), HttpStatus.OK);
    }

    //Change Status
    @PutMapping("changeStatus/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ordersService.changeStatus(orderId,status), HttpStatus.OK), HttpStatus.OK);
    }
}
