package com.imechanic.backend.principal_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTOResponse {
    private String plate;
    private String brand;
    private String model;
    private String category;
}
