package com.ra.md4projectapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="full_address")
    private String address;
    @Column(name="phone",length = 15)
    private String phone;
    @Column(name="receive_name",length = 50)
    private String receiveName;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
