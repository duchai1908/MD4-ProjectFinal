package com.ra.md4projectapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true, length = 100,name="product_name")
    private String name;
    @Column(length = 100,unique = true)
    @Builder.Default
    private String sku= UUID.randomUUID().toString();
    private String description;
    @Column(columnDefinition = "Decimal(10,2)")
    private Double price;
    @Min(0)
    private Integer stock;
    private String image;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date created_at;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updated_at;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
