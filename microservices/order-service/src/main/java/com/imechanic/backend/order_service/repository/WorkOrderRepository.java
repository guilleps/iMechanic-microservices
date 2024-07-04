package com.imechanic.backend.order_service.repository;

import com.imechanic.backend.order_service.model.WorkOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends MongoRepository<WorkOrder, String> {
    List<WorkOrder> findByEmailWorkshopOrderByWorkOrderDateDesc(String emailWorkshop);

    List<WorkOrder> findByEmailCustomerOrderByWorkOrderDateDesc(String emailCustomer);
}
