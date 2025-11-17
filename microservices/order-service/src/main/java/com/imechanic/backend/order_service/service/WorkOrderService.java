package com.imechanic.backend.order_service.service;

import com.imechanic.backend.order_service.client.*;
import com.imechanic.backend.order_service.controller.request.AssignmentDTORequest;
import com.imechanic.backend.order_service.controller.request.WorkOrderDTORequest;
import com.imechanic.backend.order_service.controller.response.*;
import com.imechanic.backend.order_service.enumeration.OrderStatus;
import com.imechanic.backend.order_service.exception.ResourceNotFoundException;
import com.imechanic.backend.order_service.exception.StepCompletedException;
import com.imechanic.backend.order_service.model.MechanicStep;
import com.imechanic.backend.order_service.model.Task;
import com.imechanic.backend.order_service.model.WorkOrder;
import com.imechanic.backend.order_service.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkOrderService {
    private final WorkOrderRepository workOrderRepository;
    private final UserClient userClient;
    private final AssignmentClient assignmentClient;
    private final MechanicClient mechanicClient;
    private final OperationClient operationClient;
    private final StepClient stepClient;
    private final TaskService taskService;
    private final MechanicStepService mechanicStepService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Crea un orden de trabajo nueva
     *
     * @param workOrderDTORequest objeto DTO con información necesaria para la creacion de la nueva orden
     * @return un string que confirma la creacion de una nueva orden de trabajo, con ID
     */
    @Transactional
    public MessageResponse createWorkOrder(WorkOrderDTORequest workOrderDTORequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        WorkOrder workOrder = WorkOrder.builder()
                .plate(workOrderDTORequest.getPlate())
                .emailCustomer(workOrderDTORequest.getEmailCustomer())
                .emailWorkshop(email)
                .orderStatus(OrderStatus.EN_ESPERA)
                .workOrderDate(new Date())
                .tasks(new ArrayList<>())
                .build();

        workOrderRepository.save(workOrder);

        for (AssignmentDTORequest assignmentDTORequest : workOrderDTORequest.getAssignmentDTORequests()) {
            Long assignmentId = fetchAssignmentId(assignmentDTORequest);

            Task task = Task.builder()
                    .workOrder(workOrder)
                    .assignmentId(String.valueOf(assignmentId))
                    .build();

            taskService.createTask(task);
            workOrder.getTasks().add(task);
        }

        workOrderRepository.save(workOrder);

        return new MessageResponse("WorkOrder with ID: " + workOrder.getId() + " has been created");
    }

    /**
     * Obtiene todas las órdenes de trabajo de un TALLER
     *
     * @return una lista de clase DTO con informacion de las ordenes de trabjajo por TALLER
     */
    @Transactional(readOnly = true)
    public List<WorkOrderDTOResponse> getAllWorkshopOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = authentication.getName();
        List<WorkOrder> workOrders = workOrderRepository.findByEmailWorkshopOrderByWorkOrderDateDesc(emailUser);

        return workOrders.stream()
                .map(workOrder -> new WorkOrderDTOResponse(
                        workOrder.getId(),
                        workOrder.getPlate(),
                        dateFormat.format(workOrder.getWorkOrderDate()),
                        timeFormat.format(workOrder.getWorkOrderDate()),
                        workOrder.getOrderStatus().name()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las órdenes de trabajo asignadas al MECANICO
     *
     * @return una lista de clase DTO con informacion de las ordenes de trabjajo por MECANICO
     */
    @Transactional(readOnly = true)
    public List<MechanicWorkOrderDTOResponse> getAllMechanicWorkOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<String> assignmentIds = assignmentClient.getAssignmentIdsByMechanic(email);

        List<Task> tasks = taskService.getAllTasksByAssignmentId(assignmentIds);

        List<Long> workOrderIds = tasks.stream()
                .map(task -> task.getWorkOrder().getId())
                .collect(Collectors.toList());

        List<WorkOrder> workOrders = workOrderRepository.findAllById(workOrderIds);

        return workOrders.stream()
                .map(workOrder -> new MechanicWorkOrderDTOResponse(
                        workOrder.getId(),
                        workOrder.getPlate(),
                        dateFormat.format(workOrder.getWorkOrderDate()),
                        timeFormat.format(workOrder.getWorkOrderDate()),
                        workOrder.getOrderStatus().name()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las órdenes de trabajo del CLIENTE
     *
     * @return una lista de clase DTO con informacion de las ordenes de trabjajo por CLIENTE
     */
    @Transactional(readOnly = true)
    public List<CustomerWorkOrderDTOResponse> getAllCustomerWorkOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = authentication.getName();

        List<WorkOrder> workOrders = workOrderRepository.findByEmailCustomerOrderByWorkOrderDateDesc(emailUser);

        if (workOrders.isEmpty()) {
            throw new ResourceNotFoundException("No work orders found for customer: " + emailUser);
        }

        return workOrders.stream()
                .map(workOrder -> new CustomerWorkOrderDTOResponse(
                        workOrder.getId(),
                        workOrder.getPlate(),
                        fetchUserAttribute(workOrder.getEmailWorkshop(), "name"),
                        dateFormat.format(workOrder.getWorkOrderDate()),
                        timeFormat.format(workOrder.getWorkOrderDate()),
                        workOrder.getOrderStatus().name()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los detalles de una orden de trabajo específica
     *
     * @param workOrderId el ID de la orden a mostrar
     * @return objeto DTO con datos especificos de la ordene
     */
    @Transactional(readOnly = true)
    public WorkOrderDetailsDTOResponse getWorkOrderDetails(String workOrderId) { // probar funcionalidad detalles de la orden al ingresar

        WorkOrder workOrder = findWorkOrderById(workOrderId);

        List<Task> tasks = workOrder.getTasks();
        List<OperationDetailsDTOResponse> operationDetails = tasks.stream()
                .map(task -> assignmentClient.getOperationDetailByAssignmentId(task.getAssignmentId()))
                .collect(Collectors.toList());

        return new WorkOrderDetailsDTOResponse(
                workOrderId,
                fetchUserAttribute(workOrder.getEmailCustomer(), "name"), // buscar nombre por email workshop
                fetchUserAttribute(workOrder.getEmailCustomer(), "address"), // buscar address por email workshop
                fetchUserAttribute(workOrder.getEmailCustomer(), "phone"), // buscar phone por email workshop
                operationDetails
        );
    }

    @Transactional(readOnly = true)
    public WorkOrderDetailsDTOResponse getCustomerWorkOrderDetails(String workOrderId) { // probar funcionalidad detalles de la orden al ingresar

        WorkOrder workOrder = findWorkOrderById(workOrderId);

        List<Task> tasks = workOrder.getTasks();
        List<OperationDetailsDTOResponse> operationDetails = tasks.stream()
                .map(task -> assignmentClient.getOperationDetailByAssignmentId(task.getAssignmentId()))
                .collect(Collectors.toList());

        return new WorkOrderDetailsDTOResponse(
                workOrderId,
                fetchUserAttribute(workOrder.getEmailWorkshop(), "name"), // buscar nombre por email workshop
                fetchUserAttribute(workOrder.getEmailWorkshop(), "address"), // buscar address por email workshop
                fetchUserAttribute(workOrder.getEmailWorkshop(), "phone"), // buscar phone por email workshop
                operationDetails
        );
    }

    @Transactional(readOnly = true)
    public MechanicWorkOrderDetailsDTOResponse getMechanicWorkOrderDetails(String workOrderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailMechanic = authentication.getName();

        WorkOrder workOrder = findWorkOrderById(workOrderId);

        Task task = workOrder.getTasks().stream()
                .filter(taskService -> mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(taskService.getAssignmentId(), "email").equals(emailMechanic))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The service assigned to the mechanic was not found"));

        List<StepDTOResponse> stepDTOResponses = assignmentClient.getOperationStepsByAssignmentId(task.getAssignmentId());

        return new MechanicWorkOrderDetailsDTOResponse(
                workOrder.getId(),
                fetchUserAttribute(workOrder.getEmailCustomer(), "name"),
                fetchUserAttribute(workOrder.getEmailCustomer(), "address"),
                fetchUserAttribute(workOrder.getEmailCustomer(), "phone"),
                new OperationDTOResponse(
                        Long.valueOf(operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "id")),
                        operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "name")),
                operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "serviceStatus"),
                new MechanicDTOResponse(
                        Long.valueOf(mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "id")),
                        mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "name")),
                mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "phone"),
                stepDTOResponses
        );
    }

    @Transactional
    public MessageResponse startWorkOrderService(String workOrderId, Long operationId) {

        WorkOrder workOrder = findWorkOrderById(workOrderId);

        Task task = workOrder.getTasks().stream()
                .filter(taskService -> operationClient.getOperationAttributeByAssignmentIdAndAttribute(taskService.getAssignmentId(), "id").equals(operationId.toString()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The service assigned to the mechanic was not found"));

        if (operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "serviceStatus").equals(OrderStatus.EN_ESPERA.toString())) {
            operationClient.setOperationStatusToInProcessByAssignmentId(task.getAssignmentId());
        }

        boolean anyOperationInProgress = workOrder.getTasks().stream()
                .anyMatch(sm -> operationClient.getOperationAttributeByAssignmentIdAndAttribute(sm.getAssignmentId(), "serviceStatus").equals(OrderStatus.EN_PROCESO.toString()));

        if (anyOperationInProgress && workOrder.getOrderStatus() == OrderStatus.EN_ESPERA) {
            workOrder.setOrderStatus(OrderStatus.EN_PROCESO);
            workOrderRepository.save(workOrder);
            return new MessageResponse("UPDATED ORDER STATUS A: " + workOrder.getOrderStatus());
        }

        if (workOrder.getOrderStatus() == OrderStatus.EN_PROCESO) {
            workOrderRepository.save(workOrder);
            return new MessageResponse("SERVICE INITIATED AND ORDER ALREADY IN PROCESS");
        }

        if (workOrder.getOrderStatus() == OrderStatus.FINALIZADO) {
            return new MessageResponse("SERVICE CANNOT BE STARTED, SERVICE ALREADY TERMINATED");
        }

        return new MessageResponse("NO SERVICES IN PROCESS TO UPDATE ORDER STATUS");
    }

    @Transactional
    public MechanicStepDTOResponse completeOperationStep(String workOrderId, Long operationId, Long stepId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailMechanic = authentication.getName();

        WorkOrder workOrder = findWorkOrderById(workOrderId);

        Task task = workOrder.getTasks().stream()
                .filter(taskItem ->
                        mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(taskItem.getAssignmentId(), "email").equals(emailMechanic) &&
                                operationClient.getOperationAttributeByAssignmentIdAndAttribute(taskItem.getAssignmentId(), "id").equals(operationId.toString()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The mechanic is not assigned to the service on the work order."));

        boolean stepCompleted = mechanicStepService.existsByTaskIdAndStepId(task.getId(), stepId);
        if (stepCompleted) {
            throw new StepCompletedException("Step with ID: " + stepId + " has been completed");
        }

        if (!stepClient.existStep(stepId)) {
            throw new ResourceNotFoundException("Step with ID: not found");
        }

        MechanicStep mechanicStep = MechanicStep.builder()
                .task(task)
                .stepId(stepId.toString())
                .complete(true)
                .build();

        mechanicStepService.saveMechanicStep(mechanicStep);
        log.info("MechanicStep saved successfully: {}", mechanicStep);

        return new MechanicStepDTOResponse(
                workOrderId,
                Long.valueOf(mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "id")),
                Long.valueOf(operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "id")),
                operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "name"),
                stepId,
                mechanicStep.isComplete()
        );
    }

    /**
     * Obtiene los pasos ya completados del servicio de una orden de trabajo específica
     *
     * @param workOrderId ID de la orden especificada
     * @param operationId ID del servicio a buscar
     * @return objeto DTO con datos especificos de la orden
     */
    @Transactional(readOnly = true)
    public List<StepOrderResponse> getCompletedSteps(String workOrderId, Long operationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailMechanic = authentication.getName();

        WorkOrder workOrder = findWorkOrderById(workOrderId);

        Task task = workOrder.getTasks().stream()
                .filter(taskItem ->
                        mechanicClient.getMechanicAttributeByAssignmentIdAndAttribute(taskItem.getAssignmentId(), "email").equals(emailMechanic) &&
                                operationClient.getOperationAttributeByAssignmentIdAndAttribute(taskItem.getAssignmentId(), "id").equals(operationId.toString()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The mechanic is not assigned to the service on the work order."));

        List<MechanicStep> mechanicStepsCompleted = mechanicStepService.findAllByTask(String.valueOf(task.getId()));

        return mechanicStepsCompleted.stream()
                .map(mechanicStep -> new StepOrderResponse(
                        Long.valueOf(mechanicStep.getStepId()),
                        stepClient.getStepAttributeByStepIdAndAttribute(mechanicStep.getStepId(), "name"),
                        Integer.parseInt(stepClient.getStepAttributeByStepIdAndAttribute(mechanicStep.getStepId(), "orderStep")),
                        mechanicStep.isComplete()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StepOrderResponse> getCompletedStepsByUser(String workOrderId) {
        WorkOrder workOrder = findWorkOrderById(workOrderId);

        List<Task> tasks = workOrder.getTasks();

        List<MechanicStep> mechanicStepsCompleted = tasks.stream()
                .flatMap(task -> mechanicStepService.findAllByTask(String.valueOf(task.getId())).stream())
                .collect(Collectors.toList());

        return mechanicStepsCompleted.stream()
                .map(mechanicStep -> new StepOrderResponse(
                        Long.valueOf(mechanicStep.getStepId()),
                        stepClient.getStepAttributeByStepIdAndAttribute(mechanicStep.getStepId(), "name"),
                        Integer.parseInt(stepClient.getStepAttributeByStepIdAndAttribute(mechanicStep.getStepId(), "orderStep")),
                        mechanicStep.isComplete()))
                .collect(Collectors.toList());
    }

    @Transactional
    public MessageResponse endWorkOrderService(String workOrderId, Long operationId) {
        WorkOrder workOrder = findWorkOrderById(workOrderId);

        Task task = workOrder.getTasks().stream()
                .filter(taskService ->
                        operationClient.getOperationAttributeByAssignmentIdAndAttribute(taskService.getAssignmentId(), "id")
                                .equals(operationId.toString()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The service assigned to the mechanic was not found"));

        if (operationClient.getOperationAttributeByAssignmentIdAndAttribute(task.getAssignmentId(), "serviceStatus").equals(OrderStatus.EN_PROCESO.toString())) {
            operationClient.setOperationStatusToFinalizedByAssignmentId(task.getAssignmentId());
        } else {
            return new MessageResponse("Operation with ID: " + operationId + " is not in process, cannot be terminated.");
        }

        boolean allServicesFinalized = workOrder.getTasks().stream()
                .allMatch(sm -> operationClient
                        .getOperationAttributeByAssignmentIdAndAttribute(sm.getAssignmentId(), "serviceStatus")
                        .equals(OrderStatus.FINALIZADO.toString()));

        if (allServicesFinalized) {
            workOrder.setOrderStatus(OrderStatus.FINALIZADO);
            workOrderRepository.save(workOrder);

            return new MessageResponse("All services are finalized, work order status updated to FINALIZED.");
        }

        if (workOrder.getOrderStatus() == OrderStatus.EN_PROCESO) {
            return new MessageResponse("SERVICE INITIATED AND ORDER ALREADY IN PROCESS");
        }

        if (workOrder.getOrderStatus() == OrderStatus.EN_ESPERA) {
            return new MessageResponse("SERVICE CANNOT BE STARTED, SERVICE ALREADY TERMINATED");
        }

        if (workOrder.getOrderStatus() == OrderStatus.FINALIZADO) {
            return new MessageResponse("SERVICE CANNOT BE STARTED, SERVICE ALREADY TERMINATED");
        }

        return new MessageResponse("NO SERVICES IN PROCESS TO UPDATE ORDER STATUS");
    }

    private WorkOrder findWorkOrderById(String workOrderId) {
        return workOrderRepository.findById(Long.valueOf(workOrderId))
                .orElseThrow(() -> new ResourceNotFoundException("WorkOrder with ID: " + workOrderId + " not found"));
    }

    private String fetchUserAttribute(String email, String attribute) {
        try {
            log.info("Fetching workshop attribute for attribute: {}", attribute);
            return userClient.getAttributeUserByEmail(email, attribute);
        } catch (Exception e) {
            log.error("Error fetching workshop attribute for user: {}", attribute, e);
            throw new RuntimeException("Could not fetch workshop attribute", e);
        }
    }

    private Long fetchAssignmentId(AssignmentDTORequest request) {
        try {
            log.info("Fetching assignment id for operationId: {} and mechanicId: {}", request.getOperationId(), request.getMechanicId());
            return assignmentClient.getAssignmentIdByOperationIdAndMechanicId(request.getOperationId(), request.getMechanicId());
        } catch (Exception e) {
            log.error("Error fetching assignment id for operationId: {} and mechanicId: {}", request.getOperationId(), request.getMechanicId(), e);
            throw new RuntimeException("Could not fetch assignment id", e);
        }
    }
}
