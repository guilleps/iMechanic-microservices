package com.imechanic.backend.order_service.controller;

import com.imechanic.backend.order_service.controller.request.*;
import com.imechanic.backend.order_service.controller.response.*;
import com.imechanic.backend.order_service.service.WorkOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class WorkOrderController {
    private final WorkOrderService workOrderService;

    // CREAR ORDEN DE TRABAJO
    @PostMapping("/crear")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<MessageResponse> save(@RequestBody WorkOrderDTORequest workOrderDTORequest) {
        return ResponseEntity.ok(workOrderService.createWorkOrder(workOrderDTORequest));
    }

    // TRAER TODAS LAS ORDENES DE TRABAJO DE TALLER
    @GetMapping("/all/workshop")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<List<WorkOrderDTOResponse>> getAllWorkshopOrders() {
        List<WorkOrderDTOResponse> workshopOrders = workOrderService.getAllWorkshopOrders();
        return ResponseEntity.ok(workshopOrders);
    }

    // TRAER DETALLE DE ORDEN DEL TALLER
    @GetMapping("/order-detail/{workOrderId}/workshop")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<WorkOrderDetailsDTOResponse> getWorkOrderDetails(@PathVariable("workOrderId") String workOrderId) {
        return ResponseEntity.ok(workOrderService.getWorkOrderDetails(workOrderId));
    }

    // TRAER TODAS LAS ORDENES DE CLIENTE
    @GetMapping("/all/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<CustomerWorkOrderDTOResponse>> getAllCustomerWorkOrders() {
        List<CustomerWorkOrderDTOResponse> customerWorkOrderS = workOrderService.getAllCustomerWorkOrders();
        return ResponseEntity.ok(customerWorkOrderS);
    }

    // TRAER DETALLE DE ORDEN DEL CLIENTE
    @GetMapping("/order-detail/{workOrderId}/customer")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<WorkOrderDetailsDTOResponse> getCustomerWorkOrderDetails(@PathVariable("workOrderId") String workOrderId) {
        return ResponseEntity.ok(workOrderService.getCustomerWorkOrderDetails(workOrderId));
    }

    // TRAER TODAS LAS ORDENES DE MECANICO
    @GetMapping("/all/mechanic")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public ResponseEntity<List<MechanicWorkOrderDTOResponse>> getAllMechanicWorkOrders() {
        List<MechanicWorkOrderDTOResponse> mechanicWorkOrderS = workOrderService.getAllMechanicWorkOrders();
        return ResponseEntity.ok(mechanicWorkOrderS);
    }

    // TRAER DETALLE DE ORDEN DEL MECANICO
    @GetMapping("/order-detail/{workOrderId}/mechanic")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public ResponseEntity<MechanicWorkOrderDetailsDTOResponse> getMechanicWorkOrderDetails(@PathVariable("workOrderId") String workOrderId) {
        return ResponseEntity.ok(workOrderService.getMechanicWorkOrderDetails(workOrderId));
    }

    @PutMapping("/iniciar/{workOrderId}/servicio/{operationId}")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public ResponseEntity<MessageResponse> startWorkOrderService(@PathVariable("workOrderId") String workOrderId, @PathVariable("operationId") Long operationId) {
        return ResponseEntity.ok(workOrderService.startWorkOrderService(workOrderId, operationId));
    }

    @PutMapping("/{workOrderId}/service/{operationId}/paso/{stepId}/complete")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public ResponseEntity<MechanicStepDTOResponse> completeTheOperationStep(@PathVariable String workOrderId,
                                                         @PathVariable Long operationId,
                                                         @PathVariable Long stepId) {
        return ResponseEntity.ok(workOrderService.completeOperationStep(workOrderId, operationId, stepId));
    }

    @GetMapping("/{workOrderId}/service/{operationId}/complete-list")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public ResponseEntity<?> getCompletedSteps(@PathVariable("workOrderId") String workOrderId, @PathVariable("operationId") Long operationId) {
        return ResponseEntity.ok(workOrderService.getCompletedSteps(workOrderId, operationId));
    }

    @PutMapping("/terminar/{workOrderId}/servicio/{operationId}")
    @PreAuthorize("hasAuthority('MECHANIC')")
    public ResponseEntity<MessageResponse> endWorkOrderService(@PathVariable("workOrderId") String workOrderId, @PathVariable("operationId") Long operationId) {
        return ResponseEntity.ok(workOrderService.endWorkOrderService(workOrderId, operationId));
    }

    @GetMapping("/order-detail/{workOrderId}/operations")
    @PreAuthorize("hasAnyAuthority('WORKSHOP','CUSTOMER')")
    public ResponseEntity<List<StepOrderResponse>> getCompletedStepsByUser(@PathVariable("workOrderId") String workOrderId) {
        return ResponseEntity.ok(workOrderService.getCompletedStepsByUser(workOrderId));
    }
}
