package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MechanicWorkOrderDTOResponse {
    private String id;
    private String plate;
    private String orderDate;
    private String hourDate;
    private String status;
}