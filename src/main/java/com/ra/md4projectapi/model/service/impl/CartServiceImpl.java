package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.constants.Status;
import com.ra.md4projectapi.model.dto.request.CartItemRequest;
import com.ra.md4projectapi.model.dto.request.OrdersRequest;
import com.ra.md4projectapi.model.entity.*;
import com.ra.md4projectapi.model.repository.ICartUserRepository;
import com.ra.md4projectapi.model.repository.IOrderDetailRepository;
import com.ra.md4projectapi.model.repository.IOrdersRepository;
import com.ra.md4projectapi.model.repository.IProductRepository;
import com.ra.md4projectapi.model.service.IAddressService;
import com.ra.md4projectapi.model.service.ICartService;
import com.ra.md4projectapi.model.service.IProductService;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final ICartUserRepository cartUserRepository;
    private final IUserService userService;
    private final IProductService productService;
    private final IAddressService addressService;
    private final IOrdersRepository ordersRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductRepository productRepository;

    // Add to cart
    @Override
    public CartItem add(CartItemRequest cartItemRequest) {
        CartItem cartItem = new CartItem();
        if(cartUserRepository.findCartItemByUserIdAndProductId(cartItemRequest.getUser(),cartItemRequest.getProduct()) != null){
               cartItem = cartUserRepository.findCartItemByUserIdAndProductId(cartItemRequest.getUser(),cartItemRequest.getProduct());
               cartItem.setQuantity(cartItemRequest.getQuantity() + cartItem.getQuantity());
        }else{
            cartItem = CartItem.builder()
                    .user(userService.getUserById(cartItemRequest.getUser()))
                    .product(productService.findByProductId(cartItemRequest.getProduct()))
                    .quantity(cartItemRequest.getQuantity())
                    .build();
        }

       return cartUserRepository.save(cartItem);
    }

    // List Cart
    @Override
    public List<CartItem> getCartItems(Long userId) {
        return cartUserRepository.findCartItemByUserId(userId);
    }

    // Change Quantity
    @Override
    public CartItem changeQuantity(Long cartItemId, Integer quantity) {
        CartItem item = cartUserRepository.findById(cartItemId).orElseThrow(()-> new NoSuchElementException("Cart not found"));
        item.setQuantity(quantity);
        return cartUserRepository.save(item);
    }

    // Delete CartItem By Id
    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem item = cartUserRepository.findById(cartItemId).orElseThrow(()-> new NoSuchElementException("Cart not found"));
        cartUserRepository.delete(item);
    }

    // Delete All CartItem
    @Override
    public void deleteAllCartItems(Long userId) {
        cartUserRepository.deleteAllByUserId(userId);
    }

    // Add to Orders
    @Override
    public Orders addOrders(OrdersRequest ordersRequest,Long userId) {
        // Address UserLogin
        Address address = addressService.findById(ordersRequest.getAddressId(),userId);

        List<CartItem> cartItems = cartUserRepository.findCartItemByUserId(userId);
        if(cartItems.isEmpty()){
            throw new NoSuchElementException("Your cart is empty");
        }
        // Total Price
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        //Sku
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        // ReceivedAt and Created At
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 4);
        Date futureDate = calendar.getTime();

        // Created Order
        Orders orders;
        orders = Orders.builder()
                .user(userService.getUserById(userId))
                .note(ordersRequest.getNote())
                .code(uuidString)
                .status(Status.WAITING)
                .receivePhone(address.getPhone())
                .receiveName(address.getReceiveName())
                .receiveAddress(address.getReceiveName())
                .totalPrice(totalPrice)
                .createdAt(currentDate)
                .receivedAt(futureDate)
                .build();
        orders = ordersRepository.save(orders);

        // Created Order Detail
        for (CartItem cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setOrder(orders);
            orderDetail.setProductName(cartItem.getProduct().getName());
            orderDetailRepository.save(orderDetail);
            Product product = productService.findByProductId(cartItem.getProduct().getId());
            product.setStock(product.getStock() - orderDetail.getQuantity());
            productRepository.save(product);
        }
        // Delete CartItem
        cartUserRepository.deleteAllByUserId(userId);
        return orders;
    }
}
