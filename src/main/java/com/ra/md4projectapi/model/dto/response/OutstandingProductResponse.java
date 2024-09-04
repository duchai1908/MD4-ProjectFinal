package com.ra.md4projectapi.model.dto.response;

import com.ra.md4projectapi.model.entity.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OutstandingProductResponse {
    private Product product;
    private Double rating;
}
