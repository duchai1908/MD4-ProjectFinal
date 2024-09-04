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
public class RatingRequest {
    @NotNull(message = "Rating must not be null")
    private int rating;
    @NotBlank(message = "Comment must not be null")
    private String comment;
    private Long product;
}
