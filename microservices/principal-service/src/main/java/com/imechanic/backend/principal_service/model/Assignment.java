package com.imechanic.backend.principal_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignment")
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", referencedColumnName = "id")
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation operation;
}
