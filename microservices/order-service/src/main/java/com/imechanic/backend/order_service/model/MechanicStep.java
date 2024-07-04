package com.imechanic.backend.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "operation_step")
public class MechanicStep {

    @Id
    private String id;
    private boolean complete;
    private String stepId;

    @DocumentReference(lazy = true)
    private Task task;
}
