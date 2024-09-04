package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.constants.Status;
import com.ra.md4projectapi.model.entity.OrderDetail;
import com.ra.md4projectapi.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByStatus(Status status);
    List<Orders> findAllByUserId(Long userId);
    Optional<Orders> findByCodeAndUserId(String code,Long userId);
    List<Orders> findAllByUserIdAndStatus(Long userId,Status status);
    Optional<Orders> findByIdAndUserId(Long id, Long userId);
}
