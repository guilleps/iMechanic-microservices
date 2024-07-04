package com.imechanic.backend.principal_service.model;

import com.imechanic.backend.principal_service.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String plate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private Model model;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity userEntity;
}
