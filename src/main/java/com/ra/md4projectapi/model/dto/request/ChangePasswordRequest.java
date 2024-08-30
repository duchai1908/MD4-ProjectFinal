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
public class ChangePasswordRequest {
    @NotBlank(message = "Old password must not be null")
    private String oldPassword;
    @NotBlank(message = "New password must not be null")
    private String newPassword;
    @NotBlank(message = "Confirm password must not be null")
    private String confirmPassword;
}
