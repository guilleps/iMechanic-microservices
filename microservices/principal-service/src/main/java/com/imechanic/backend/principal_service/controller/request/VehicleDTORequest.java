package com.imechanic.backend.principal_service.controller.request;

import com.imechanic.backend.principal_service.enumeration.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTORequest {

    @NotBlank(message = "The 'plate' field is required")
    private String plate;

    @NotBlank(message = "The 'brandId' field is required")
    private Long brandId;

    @NotBlank(message = "The 'modelId' field is required")
    private Long modelId;

    @NotBlank(message = "The 'category' field is required")
    private Category category;
}

