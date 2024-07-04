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
@Table(name = "users")
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(targetEntity = Vehicle.class, fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<Vehicle> vehicles;

    @ManyToMany(targetEntity = Operation.class, fetch = FetchType.LAZY)
    private List<Operation> operations;

    @OneToMany(targetEntity = Mechanic.class, fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<Mechanic> mechanics;
}
