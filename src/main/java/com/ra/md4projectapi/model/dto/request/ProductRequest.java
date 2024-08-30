package com.ra.md4projectapi.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Product name must not be null")
    private String name;
    private String sku;
    private String description;
    @Min(value = 1,message = "price must equal or greater than 1")
    private Double price;
    @Min(value = 0,message = "Stock must equal or greater than 0")
    private Integer stock;
    private MultipartFile image;
    @NotNull(message = "Status must not be null")
    private Boolean status;
    @NotNull(message = "Category id must not be null")
    private Long category;
}
