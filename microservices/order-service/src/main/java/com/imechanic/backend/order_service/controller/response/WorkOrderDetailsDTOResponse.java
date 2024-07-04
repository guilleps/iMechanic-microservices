package com.imechanic.backend.order_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderDetailsDTOResponse {
    private String workOrderId;
    private String nameWorkshop;
    private String addressWorkshop;
    private String phoneWorkShop;
    private List<OperationDetailsDTOResponse> operationDetails;
}
