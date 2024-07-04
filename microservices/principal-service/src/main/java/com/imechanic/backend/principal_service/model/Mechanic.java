package com.imechanic.backend.principal_service.model;

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
@Table(name = "mechanic")
@Builder
public class Mechanic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String email;
    private String name;
    private String password;
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "mechanic")
    private List<Assignment> assignments;
}
