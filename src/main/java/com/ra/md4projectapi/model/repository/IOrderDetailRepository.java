package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
