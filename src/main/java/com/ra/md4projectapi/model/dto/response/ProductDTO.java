package com.ra.md4projectapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.md4projectapi.model.entity.Category;
import com.ra.md4projectapi.model.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO{
    private Product product;
    private Long total;
}
