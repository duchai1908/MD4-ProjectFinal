package com.ra.md4projectapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @Column(name="product_name")
    private String productName;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders order;

    @Column(name="quantity",nullable = false)
    private Integer quantity;
}
