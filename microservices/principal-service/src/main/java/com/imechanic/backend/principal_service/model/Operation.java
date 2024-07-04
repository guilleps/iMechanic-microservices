package com.imechanic.backend.principal_service.model;
import com.imechanic.backend.principal_service.enumeration.ServiceStatus;
import com.imechanic.backend.principal_service.enumeration.TypeService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operation")
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_service", nullable = false)
    private TypeService typeService;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_status", nullable = false)
    private ServiceStatus serviceStatus;

    @OneToMany(targetEntity = Step.class, fetch = FetchType.LAZY, mappedBy = "operation")
    private List<Step> steps;
}