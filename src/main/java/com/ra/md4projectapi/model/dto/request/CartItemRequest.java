package com.ra.md4projectapi.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemRequest {
    private Long user;
    @NotNull(message = "Product Id Is Empty!")
    private Long product;
    @Min(value = 1, message = "Quantity must equal or greater than 1")
    private Integer quantity;
}
