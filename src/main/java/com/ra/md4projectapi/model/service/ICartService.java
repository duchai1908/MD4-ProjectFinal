package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.model.dto.request.CartItemRequest;
import com.ra.md4projectapi.model.dto.request.OrdersRequest;
import com.ra.md4projectapi.model.entity.CartItem;
import com.ra.md4projectapi.model.entity.Orders;

import java.util.List;

public interface ICartService {
    CartItem add(CartItemRequest cartItemRequest);
    List<CartItem> getCartItems(Long userId);
    CartItem changeQuantity(Long cartItemId, Integer quantity);
    void deleteCartItem(Long cartItemId);
    void deleteAllCartItems(Long userId);
    Orders addOrders(OrdersRequest ordersRequest,Long userId);
}
