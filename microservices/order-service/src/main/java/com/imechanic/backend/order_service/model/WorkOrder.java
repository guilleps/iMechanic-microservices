package com.imechanic.backend.order_service.model;

import com.imechanic.backend.order_service.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "work_order")
public class WorkOrder {

    @Id
    private String id;
    private String plate;
    private String emailCustomer;
    private String emailWorkshop;

    @Field(targetType = FieldType.STRING)
    private OrderStatus orderStatus;

    @Indexed(direction = IndexDirection.DESCENDING)
    private Date workOrderDate;

    @DocumentReference(lazy = true)
    private List<Task> tasks;
}
