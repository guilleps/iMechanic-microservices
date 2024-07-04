package com.imechanic.backend.principal_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDetailsDTOResponse {
    private String nameCustomer;
    private String emailCustomer;
    private String addressCustomer;
    private String phoneCustomer;
    private String plate;
    private String brand;
    private String model;
    private String category;
}