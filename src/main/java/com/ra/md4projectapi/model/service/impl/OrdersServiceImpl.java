package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.constants.Status;
import com.ra.md4projectapi.model.dto.response.OrderResponse;
import com.ra.md4projectapi.model.entity.OrderDetail;
import com.ra.md4projectapi.model.entity.Orders;
import com.ra.md4projectapi.model.repository.IOrderDetailRepository;
import com.ra.md4projectapi.model.repository.IOrdersRepository;
import com.ra.md4projectapi.model.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements IOrdersService {
    private final IOrdersRepository ordersRepository;
    private final IOrderDetailRepository orderDetailRepository;
    // Get All Orders
    @Override
    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    //Get OrderById
    @Override
    public Orders getOrdersById(Long id) {
        return ordersRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Orders Not Found"));
    }

    // Get All Orders By Status
    @Override
    public List<Orders> findByStatus(String status) {
        Status s;
        try {
             s = Status.valueOf(status);
        }catch (Exception e){
            throw new NoSuchElementException("Status Not Found");
        }
        return ordersRepository.findAllByStatus(s);
    }

    // Change status OrderById
    @Override
    public Orders changeStatus(Long id, String status) {
        Orders orders = ordersRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Orders Not Found"));
        Status s;
        try {
            s = Status.valueOf(status);
        }catch (Exception e){
            throw new NoSuchElementException("Status Not Found");
        }
        orders.setStatus(s);
        return ordersRepository.save(orders);
    }

    // Get All Orders User
    @Override
    public List<Orders> getAllOrdersByUserLogin(Long userId) {
        return ordersRepository.findAllByUserId(userId);
    }

    // Get OrderByCode
    @Override
    public OrderResponse getOrdersBySku(String sku, Long id) {
        Orders orders = ordersRepository.findByCodeAndUserId(sku,id).orElseThrow(()-> new NoSuchElementException("Orders Not Found"));
        List<OrderDetail> listOrderDetail= orderDetailRepository.findAllByOrderId(orders.getId());
        OrderResponse orderResponse = OrderResponse.builder()
                .order(orders)
                .orderDetails(listOrderDetail)
                .build();
        return orderResponse;
    }

    //Get OrderByStatus
    @Override
    public List<Orders> findOrderByStatus(String status, Long id) {
        Status s;
        try {
            s = Status.valueOf(status);
        }catch (Exception e){
            throw new NoSuchElementException("Status Not Found");
        }
        return ordersRepository.findAllByUserIdAndStatus(id,s);
    }

    // Cancel Order When Status Is WAITING
    @Override
    public Orders cancelOrder(Long orderId, Long userId) {
        Orders orders = ordersRepository.findByIdAndUserId(orderId,userId).orElseThrow(()-> new NoSuchElementException("Orders Not Found"));
        if(orders.getStatus() == Status.WAITING){
            orders.setStatus(Status.CANCEL);
            ordersRepository.save(orders);
        }
        return orders;
    }
}
