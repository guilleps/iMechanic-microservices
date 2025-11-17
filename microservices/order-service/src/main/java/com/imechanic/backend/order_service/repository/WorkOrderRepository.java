package com.imechanic.backend.order_service.repository;

import com.imechanic.backend.order_service.model.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findByEmailWorkshopOrderByWorkOrderDateDesc(String emailWorkshop);

    List<WorkOrder> findByEmailCustomerOrderByWorkOrderDateDesc(String emailCustomer);
}
