package com.ra.md4projectapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ra.md4projectapi.model.entity.OrderDetail;
import com.ra.md4projectapi.model.entity.Orders;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Orders order;
    @JsonIgnoreProperties({"order"}) // Bỏ qua thuộc tính Order trong OrderDetail
    private List<OrderDetail> orderDetails;
}
