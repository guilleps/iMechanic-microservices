package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicDTOResponse {
    private Long id;
    private String name;
}