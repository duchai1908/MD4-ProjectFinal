package com.ra.md4projectapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAccountRequest {
    @NotBlank(message = "User name must not be null")
    private String username;
    @NotBlank(message = "Full name must not be null")
    private String fullName;
    @NotBlank(message = "Email must not be null")
    private String email;
    @NotBlank(message = "Phone number must not be null")
    private String phone;
    @NotBlank(message = "Address must not be null")
    private String address;
    @NotNull(message = "DOB must not be null")
    private Date dob;
    private MultipartFile image;
}
