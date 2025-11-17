package com.imechanic.backend.order_service.model;

import com.imechanic.backend.order_service.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "work_order")
@Builder
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String plate;
    private String emailCustomer;
    private String emailWorkshop;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "work_order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workOrderDate;

    @OneToMany(mappedBy = "workOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
}
