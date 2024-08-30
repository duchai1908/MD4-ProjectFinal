package com.ra.md4projectapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormCategory {
    @NotBlank(message = "Category name must not be null")
    private String name;
    @NotNull(message = "Status must not be null")
    private Boolean status;
    private String description;
}
