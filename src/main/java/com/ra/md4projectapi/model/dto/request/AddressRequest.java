package com.ra.md4projectapi.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressRequest {
    @NotBlank(message = "Address must not be null")
    private String address;
    @NotBlank(message = "Phone must not be null")
    private String phone;
    @NotBlank(message = "Receive must not be null")
    private String receiveName;
}
