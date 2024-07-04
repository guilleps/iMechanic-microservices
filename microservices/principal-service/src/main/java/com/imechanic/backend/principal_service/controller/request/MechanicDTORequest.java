package com.imechanic.backend.principal_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicDTORequest {

    @NotBlank(message = "The 'email' field is required")
    @Email(message = "The 'email' field must have a valid format")
    @Size(max = 30, message = "The 'email' field must not exceed 30 characters")
    private String email;

    @NotBlank(message = "The 'name' field is required")
    @Size(min = 5, message = "The 'name' field must have at least 5 characters")
    @Size(max = 20, message = "The 'name' field must not exceed 20 characters")
    private String name;

    @NotBlank(message = "The 'password' field is required")
    @Size(min = 8, message = "The 'password' field must have at least 8 characters")
    @Size(max = 20, message = "The 'password' field must not exceed 20 characters")
    private String password;

    @NotBlank(message = "The 'phone' field is required")
    @Pattern(regexp = "^[0-9]{9}$", message = "The phone must contain only 9 numbers")
    private String phone;
    private List<Long> operationsIds;
}
